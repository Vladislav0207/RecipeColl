package com.example.recipecoll2.repository

import android.util.Log
import com.example.recipecoll2.localModel.LocalIngredient
import com.example.recipecoll2.localModel.LocalModel
import com.example.recipecoll2.localModel.LocalRecipe
import com.example.recipecoll2.localModel.LocalRecipeWithIngredients
import com.example.recipecoll2.remoteModel.*

class Repository (
                  val remoteModel: RemoteModel,
                  val localModel: LocalModel
) {
    suspend fun getData(): MutableList<Recipe> {
 //       val recipeList = remoteModel.getRemoteDataRecipe()
        val recipeList = null
        return if (recipeList != null) {

//            Log.d("!!!loc1", recipeList.toString())
//            //add recipe and ingredients in database
//
//            Log.d("!!!size", recipeList.size.toString())
//            Log.d("!!!loc2", parseToLocal(recipeList).first.toString())
//            Log.d("!!!loc2", parseToLocal(recipeList).second.toString())
//                localModel.insertRecipes(parseToLocal(recipeList).first)
//                localModel.insertIngredients(parseToLocal(recipeList).second)


           val localRecipe = localModel.getAllRecipes()


//            Log.d("!!!loc3",localRecipe.toString())
//
//            Log.d("!!!loc4", parseLocalTo(localRecipe).toString())

            val recipe = parseLocalTo(localRecipe)

            recipe


        } else {
            val localRecipe = localModel.getAllRecipes()
            parseLocalTo(localRecipe)
        }
    }

    suspend fun saveData(localRecipeList: MutableList<LocalRecipe>) {
        localModel.insertRecipes(localRecipeList)
    }

    suspend fun saveOneRecipe(localRecipe: LocalRecipe) {
        localModel.insertOneRecipe(localRecipe)
    }

    suspend fun getOneRecipe(id: Int): Recipe {
        val localRecipe = mutableListOf<LocalRecipe>()
        localRecipe.add(localModel.getOneRecipe(id))
        val recipe = parseLocalTo(localRecipe)
        return recipe[0]
    }


    suspend fun parseLocalTo(listLocalRecipe: List<LocalRecipe>): MutableList<Recipe> {
        val listRecipe = mutableListOf<Recipe>()
        val listIngredients = mutableListOf<Ingredient>()
        for (i in 0 until listLocalRecipe.size) {
            val listLocalIngredients = localModel.getAllIngredientsByRecipeId(listLocalRecipe[i].id)
            for (j in 0 until listLocalIngredients.size) {
                listIngredients.add(
                    Ingredient(
                        listLocalIngredients[j].ingredientId,
                        listLocalIngredients[j].IngredientImage,
                        listLocalIngredients[j].nameClean,
                        listLocalIngredients[j].amount,
                        listLocalIngredients[j].unit,
                        listLocalIngredients[j].recipeId
                    )
                )
            }
            listRecipe.add(
                    Recipe(
                        listIngredients,
                        listLocalRecipe[i].id,
                        listLocalRecipe[i].title,
                        listLocalRecipe[i].readyInMinutes,
                        listLocalRecipe[i].servings,
                        listLocalRecipe[i].image,
                        listLocalRecipe[i].instructions
                    )
                )

        }
        return listRecipe
    }


    fun parseToLocal(recipeList: MutableList<Recipe>): Pair<MutableList<LocalRecipe>, MutableList<LocalIngredient>> {
        val localRecipeList = mutableListOf<LocalRecipe>()
        val localIngredientList = mutableListOf<LocalIngredient>()
        Log.d("!!!111", recipeList.toString())
        for (i in 0 until recipeList.size) {

            Log.d("!!!122", i.toString())

            // parsing Recipe to LocalRecipe

            localRecipeList.add(
                LocalRecipe(
                    recipeList[i].id,
                    recipeList[i].title,
                    recipeList[i].readyInMinutes,
                    recipeList[i].servings,
                    recipeList[i].image,
                    recipeList[i].instructions
                )
            )

            Log.d("!!!localRecipeList", localRecipeList.toString())

            //parsing Recipe to LocalIngredients and add recipeId
            for (j in 0 until recipeList[i].extendedIngredients.size)
                localIngredientList.add(
                    LocalIngredient(
                        recipeList[i].extendedIngredients[j].id,
                        recipeList[i].extendedIngredients[j].image,
                        recipeList[i].extendedIngredients[j].nameClean,
                        recipeList[i].extendedIngredients[j].amount,
                        recipeList[i].extendedIngredients[j].unit,
                        recipeList[i].id
                    )
                )
            Log.d("!!!localIngredientList!!", localIngredientList.toString())
        }

        Log.d("!!!ret", localRecipeList.toString())
        return Pair(localRecipeList, localIngredientList)
    }
}