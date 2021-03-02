package com.example.recipecoll2.repository

import android.util.Log
import com.example.recipecoll2.localModel.LocalModel
import com.example.recipecoll2.localModel.LocalRecipe
import com.example.recipecoll2.remoteModel.*
import javax.inject.Inject

class Repository @Inject constructor (
                  val remoteModel: RemoteModel,
                  val localModel: LocalModel
) {
    suspend fun getData(): MutableList<Recipe> {
  //    val recipeList = remoteModel.getRemoteDataRecipe()
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
                it.instructions,
                it.isFavorite)}

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


    suspend fun updateRecipe(recipeId:Int,isSelected:Int){
        localModel.updateRecipe(recipeId, isSelected)
    }

    suspend fun getAllIngredients():List<Ingredient>{
        return localModel.getAllIngredients()
    }

    suspend fun searchByIngredient (listOfNames : MutableList<String>) : MutableList<Recipe>{
       val recipeList= getData()
        val resultList = mutableListOf<Recipe>()

        for (i in 0 until  recipeList.size){
//            recipeList[i]
            var h =0
            for (j in 0 until  recipeList[i].extendedIngredients.size) {
//                recipeList[i].extendedIngredients[j]
                for (k in 0 until  listOfNames.size){
                    if (recipeList[i].extendedIngredients[j].nameClean == listOfNames[k]){
                        h+=1
                    }
                }
            }
            if (h == listOfNames.size){
                resultList.add(recipeList[i])
            }
        }
        return resultList
    }
}