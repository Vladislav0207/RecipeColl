package com.example.recipecoll2.remoteModel

import java.lang.Exception

class RemoteModel() {
    private val apiService = ApiService.create()
    suspend fun getRemoteDataRecipe() : MutableList<Recipe>{
        return try {
            val  recipesList = apiService.getRecipes(KEY, HOST, NUMBER).recipes
            for (i in 0 until NUMBER){
                recipesList[i].extendedIngredients.forEach{it.recipe_id = recipesList[i].id }
            }

            recipesList
        }
        catch (e : Exception){
            mutableListOf()
        }
    }
}