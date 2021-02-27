package com.example.recipecoll2.repository

import com.example.recipecoll2.localModel.LocalIngredient
import com.example.recipecoll2.localModel.LocalModel
import com.example.recipecoll2.localModel.LocalRecipe
import com.example.recipecoll2.remoteModel.*

class Repository (
                  val remoteModel: RemoteModel,
                  val localModel: LocalModel
)
{
    suspend fun getData() : MutableList<Recipe> {
        val localRecipeList: MutableList<LocalRecipe>? = null
        val localIngredientList : MutableList<LocalIngredient>? = null
       // var allRecipeList = localModel.getAllRecipes()
        return if (remoteModel != null) {

          val recipeList = remoteModel.getRemoteDataRecipe()
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

            //add recipe and ingredients in database
            localRecipeList.let {
                localModel.insertRecipes(it!!)
            }
            localIngredientList.let {
                localModel.insertIngredients(it!!)
            }

       }
        else {
            localRecipeList
        }
    }

    suspend fun saveData(localRecipeList : MutableList<LocalRecipe>){
        localModel.insertRecipes(localRecipeList)
    }

    suspend fun saveOneRecipe (localRecipe: LocalRecipe) {
        localModel.insertOneRecipe(localRecipe)
    }

    suspend fun getOneRecipe(id: Int) : LocalRecipe{
        return localModel.getOneRecipe(id)
    }
}