package com.example.recipecoll2.localModel

import android.content.Context
import androidx.room.Room

class LocalModel (context: Context) {
    val recipeDatabase : RecipeDatabase = Room.databaseBuilder(context,
        RecipeDatabase::class.java,
        "recipe_db")
        .build()

    val ingredientDatabase : IngredientDatabase = Room.databaseBuilder(context,
        IngredientDatabase::class.java,
        "ingredient_db")
        .build()

    suspend fun insertRecipes(recipes : MutableList<Recipe>){
        recipeDatabase.recipeDao().insertRecipe(recipes)
    }

    suspend fun insertOneRecipe(recipe: Recipe){
        recipeDatabase.recipeDao().insertOneRecipe(recipe)
    }

    suspend fun getAllRecipes(): MutableList<Recipe>{
        return  recipeDatabase.recipeDao().getAllRecipes()
    }

    suspend fun getOneRecipe(id: Int): Recipe {
        return  recipeDatabase.recipeDao().getOneRecipe(id)
    }
}