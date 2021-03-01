package com.example.recipecoll2.repository

import android.util.Log
import com.example.recipecoll2.localModel.LocalModel
import com.example.recipecoll2.localModel.LocalRecipe
import com.example.recipecoll2.remoteModel.*

class Repository (
                  val remoteModel: RemoteModel,
                  val localModel: LocalModel
) {
    suspend fun getData(): MutableList<Recipe> {
//        val recipeList = remoteModel.getRemoteDataRecipe()
        val recipeList : MutableList<Recipe>? = null
        Log.d("!!!","recipeList")
        return if (recipeList != null) {

            Log.d("!!!","start")

            val localRecipeList = mutableListOf<LocalRecipe>()
            recipeList.mapTo(localRecipeList){LocalRecipe( it.id,
                it.title,
                it.readyInMinutes,
                it.servings,
                it.image,
                it.instructions)}

            Log.d("!!!",localRecipeList.toString())
            localModel.insertRecipes(localRecipeList)
            Log.d("!!!","pisda")
            for (i in 0 until recipeList.size ) {
                Log.d("!!!",recipeList[i].extendedIngredients.toString())
                localModel.insertIngredients(recipeList[i].extendedIngredients)
            }
            Log.d("!!!","ingrid")
            Log.d("!!!","pisda")
           val finishList = localModel.getAllRecipes()
            finishList.toMutableList()


        } else {
            val finishList = localModel.getAllRecipes()
            finishList.toMutableList()
        }
    }

//    suspend fun saveData(localRecipeList: MutableList<LocalRecipe>) {
//        localModel.insertRecipes(localRecipeList)
//    }

//    suspend fun saveOneRecipe(localRecipe: LocalRecipe) {
//        localModel.insertOneRecipe(localRecipe)
//    }

//    suspend fun getOneRecipe(id: Int): Recipe {
//        val localRecipe = mutableListOf<LocalRecipe>()
//        localRecipe.add(localModel.getOneRecipe(id))
//        val recipe = parseLocalTo(localRecipe)
//        return recipe[0]
//    }

}