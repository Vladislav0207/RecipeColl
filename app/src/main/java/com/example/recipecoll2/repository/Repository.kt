package com.example.recipecoll2.repository

import com.example.recipecoll2.localModel.Recipe
import com.example.recipecoll2.remoteModel.RemoteModel

class Repository (
                  val remoteModel: RemoteModel
 //                 val localModel: LocalModel
)
{
    suspend fun getData() : MutableList<Recipe> {
//        var recipeList = localModel.getAllRecipes()
//        return if (remoteModel != null) {
          val  recipeList = remoteModel.getRemoteData()
//            localModel.insertRecipes(recipeList)
    return        recipeList
       }
//        else {
//            recipeList
//        }
//    }
//
//    suspend fun saveData(recipeList : MutableList<Recipe>){
//        localModel.insertRecipes(recipeList)
//    }
//
//    suspend fun saveOneRecipe (recipe: Recipe) {
//        localModel.insertOneRecipe(recipe)
//    }
//
//    suspend fun getOneRecipe(id: Int) : Recipe{
//        return localModel.getOneRecipe(id)
//    }
}