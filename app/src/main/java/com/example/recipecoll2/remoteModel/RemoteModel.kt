package com.example.recipecoll2.remoteModel

import java.lang.Exception

class RemoteModel() {
    private val apiService = ApiService.create()
    suspend fun getRemoteData() : MutableList<AllRecipe>{
        return try {
            lateinit var allRecipesList : MutableList<AllRecipe>

            val  recipesList = apiService.getRecipes(KEY, HOST, NUMBER).recipes
            for (num in 0 until NUMBER){
                val recipe = AllRecipe(
                    recipesList[num].id,
                    recipesList[num].title,
                    recipesList[num].readyInMinutes,
                    recipesList[num].servings,
                    recipesList[num].image,
                    recipesList[num].instructions,
                    apiService.getRecipeIngredients(KEY, HOST,recipesList[num].id).ingredients
                )
                allRecipesList.add(num,recipe)

            }
          allRecipesList
        }
        catch (e : Exception){
            mutableListOf()
        }
    }
}