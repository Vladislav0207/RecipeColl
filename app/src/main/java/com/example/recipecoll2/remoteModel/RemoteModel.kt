package com.example.recipecoll2.remoteModel

import com.example.recipecoll2.localModel.Recipe
import java.lang.Exception

class RemoteModel() {
    private val apiService = ApiService.create()
    suspend fun getRemoteDataRecipe() : MutableList<Recipe>{
        return try {
            val  recipesList = apiService.getRecipes(KEY, HOST, NUMBER).recipes
            recipesList
        }
        catch (e : Exception){
            mutableListOf()
        }
    }
    suspend fun getRemoteData() : MutableList<Recipe>{
        return try {
            val  recipesList = apiService.getRecipes(KEY, HOST, NUMBER).recipes
            recipesList
        }
        catch (e : Exception){
            mutableListOf()
        }
    }
}