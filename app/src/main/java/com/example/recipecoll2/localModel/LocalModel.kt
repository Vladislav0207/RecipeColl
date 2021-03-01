package com.example.recipecoll2.localModel

import android.content.Context
import androidx.room.Room
import com.example.recipecoll2.remoteModel.Ingredient
import com.example.recipecoll2.remoteModel.Recipe

class LocalModel (context: Context) {
    //build database
    val recipeDatabase : RecipeDatabase = Room.databaseBuilder(context,
        RecipeDatabase::class.java,
        "recipe_db")
        .build()

//realization DaoFun
    suspend fun insertRecipes(localRecipes : MutableList<LocalRecipe>){
        recipeDatabase.recipeDao().insertRecipe(localRecipes)
    }

//    suspend fun insertOneRecipe(localRecipe: Recipe){
//        recipeDatabase.recipeDao().insertOneRecipe(localRecipe)
//    }

    suspend fun getAllRecipes(): List<Recipe>{
        return  recipeDatabase.recipeDao().getAllRecipe()
    }

//    suspend fun getOneRecipe(id: Int): Recipe {
//        return  recipeDatabase.recipeDao().getOneRecipe(id)
//    }

    suspend fun insertIngredients(localIngredients : List<Ingredient>){
        recipeDatabase.ingredientDao().insertAllIngredients(localIngredients)
    }

    suspend fun getAllIngredients(): List<Ingredient>{
       return recipeDatabase.ingredientDao().getAllIngredients()
    }

}