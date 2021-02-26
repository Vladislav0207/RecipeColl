package com.example.recipecoll2.repository

import com.example.recipecoll2.localModel.LocalModel
import com.example.recipecoll2.localModel.Recipe
import com.example.recipecoll2.remoteModel.*

class Repository (
                  val remoteModel: RemoteModel,
                  val localModel: LocalModel
)
{
    suspend fun getData() : MutableList<AllRecipe> {
        var recipeList = localModel.getAllRecipes()
        return if (remoteModel != null) {
          recipeList = remoteModel.getRemoteData()
            lateinit var allRecipesList : MutableList<AllRecipe>
            for (num in 0 until recipeList.size){
                val recipe = AllRecipe(
                    recipesList[num].id,
                    recipesList[num].title,
                    recipesList[num].readyInMinutes,
                    recipesList[num].servings,
                    recipesList[num].image,
                    recipesList[num].instructions,
                    apiService.getRecipeIngredients(KEY, HOST,recipesList[num].id)
                )

                allRecipesList.add(num,recipe)
            }
            localModel.insertRecipes(recipeList)
       }
        else {
            recipeList
        }
    }

    suspend fun saveData(recipeList : MutableList<Recipe>){
        localModel.insertRecipes(recipeList)
    }

    suspend fun saveOneRecipe (recipe: Recipe) {
        localModel.insertOneRecipe(recipe)
    }

    suspend fun getOneRecipe(id: Int) : Recipe{
        return localModel.getOneRecipe(id)
    }
}