package com.example.recipecoll2.remoteModel

import android.util.Log
import java.lang.Exception

class RemoteModel() {
    private val apiService = ApiService.create()
    suspend fun getRemoteData() : MutableList<Recipe>{
        return try {
            val  recipesList = apiService.getRecipes(KEY, HOST,2).recipes
            recipesList
        }
        catch (e : Exception){
            Log.d("!!!!","RemoteData exeption")
            mutableListOf<Recipe>()
        }
    }
}