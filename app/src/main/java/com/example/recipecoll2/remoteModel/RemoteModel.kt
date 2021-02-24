package com.example.recipecoll2.remoteModel

import java.lang.Exception

class RemoteModel() {
    private val apiService = ApiService.create()
    suspend fun getRemoteData() : MutableList<Recipe>{
        return try {
            val  recipesList = apiService.getRecipes(KEY, HOST,1).recipes
            recipesList
        }
        catch (e : Exception){
            mutableListOf()
        }
    }
}