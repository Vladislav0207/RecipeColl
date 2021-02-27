package com.example.recipecoll2.repository

import com.example.recipecoll2.localModel.LocalIngredient
import com.example.recipecoll2.localModel.LocalModel
import com.example.recipecoll2.localModel.LocalRecipe
import com.example.recipecoll2.localModel.LocalRecipeWithIngredients
import com.example.recipecoll2.remoteModel.*

class Repository (
                  val remoteModel: RemoteModel,
                  val localModel: LocalModel
)
{
    suspend fun getData() : MutableList<Recipe> {
        val recipeList = remoteModel.getRemoteDataRecipe()
        return if (remoteModel != null) {
            val localRecipe = localModel.getRecipeWithIngredients()
            parseToLocal(recipeList).first

            //add recipe and ingredients in database

                localModel.insertRecipes(parseToLocal(recipeList).first)
                localModel.insertIngredients(parseToLocal(recipeList).second)


            parseLocalTo(localRecipe)
       }
        else {
            val localRecipe = localModel.getRecipeWithIngredients()
                parseLocalTo(localRecipe)
        }
    }

    suspend fun saveData(localRecipeList : MutableList<LocalRecipe>){
        localModel.insertRecipes(localRecipeList)
    }

    suspend fun saveOneRecipe (localRecipe: LocalRecipe) {
        localModel.insertOneRecipe(localRecipe)
    }

    suspend fun getOneRecipe(id: Int) : Recipe{
       val LocalRecipe:MutableList<LocalRecipeWithIngredients>? = null
        LocalRecipe!!.add(localModel.getOneRecipeWithIngredients(id))
        val recipe = parseLocalTo(LocalRecipe)
        return recipe[0]
    }
}



fun parseLocalTo( recipeWithIngredients: List<LocalRecipeWithIngredients>) : MutableList<Recipe>{
    val listRecipe : MutableList<Recipe>? = null
    val listIngredients : MutableList<Ingredient>? = null
    for(i in 0 until recipeWithIngredients.size){
        for (j in 0 until recipeWithIngredients[i].localIngredient.size){
            listIngredients!!.add(Ingredient(
                recipeWithIngredients[i].localIngredient[j].ingredientId,
                recipeWithIngredients[i].localIngredient[j].IngredientImage,
                recipeWithIngredients[i].localIngredient[j].nameClean,
                recipeWithIngredients[i].localIngredient[j].amount,
                recipeWithIngredients[i].localIngredient[j].unit,
                recipeWithIngredients[i].localIngredient[j].recipeId
            ))

            listRecipe!!.add(Recipe(
             listIngredients,
                recipeWithIngredients[i].localRecipe.id,
                recipeWithIngredients[i].localRecipe.title,
                recipeWithIngredients[i].localRecipe.readyInMinutes,
                recipeWithIngredients[i].localRecipe.servings,
                recipeWithIngredients[i].localRecipe.image,
                recipeWithIngredients[i].localRecipe.instructions
            ))
        }

    }
    return listRecipe!!
}



fun parseToLocal (recipeList: MutableList<Recipe>) : Pair < MutableList<LocalRecipe>, MutableList<LocalIngredient>> {
    val localRecipeList: MutableList<LocalRecipe>? = null
    val localIngredientList : MutableList<LocalIngredient>? = null

        for (i in 0 until recipeList.size){
            // parsing Recipe to LocalRecipe
            localRecipeList!!.add(LocalRecipe(
                recipeList[i].id,
                recipeList[i].title,
                recipeList[i].readyInMinutes,
                recipeList[i].servings,
                recipeList[i].image,
                recipeList[i].instructions
            ))
            //parsing Recipe to LocalIngredients and add recipeId
            for (j in 0 until recipeList[i].extendedIngredients.size)
                localIngredientList!!.add(LocalIngredient(
                    recipeList[i].extendedIngredients[j].id,
                    recipeList[i].extendedIngredients[j].image,
                    recipeList[i].extendedIngredients[j].nameClean,
                    recipeList[i].extendedIngredients[j].amount,
                    recipeList[i].extendedIngredients[j].unit,
                    recipeList[i].id
                ))
        }

    return Pair(localRecipeList!!,localIngredientList!!)
}