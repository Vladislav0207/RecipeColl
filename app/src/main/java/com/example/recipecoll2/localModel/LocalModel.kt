package com.example.recipecoll2.localModel

import android.content.Context
import androidx.room.Room
import com.example.recipecoll2.remoteModel.Recipe

class LocalModel (context: Context) {
    val database : RecipeDatabase = Room.databaseBuilder(context,
        RecipeDatabase::class.java,
        "recipe_db")
        .build()

    suspend fun insertRecipes(recipes : MutableList<Recipe>){
        database.recipeDao().insertRecipe(recipes)
    }

    suspend fun insertOneRecipe(recipe: Recipe){
        database.recipeDao().insertOneRecipe(recipe)
    }

    suspend fun getAllRecipes(): MutableList<Recipe>{
        return database.recipeDao().getAllRecipes()
    }

    suspend fun getOneRecipe(id: Int): Recipe{
        return database.recipeDao().getOneRecipe(id)
    }
}