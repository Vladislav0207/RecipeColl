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

    suspend fun insertRecipes(localRecipes : MutableList<LocalRecipe>){
        recipeDatabase.recipeDao().insertRecipe(localRecipes)
    }

    suspend fun insertOneRecipe(localRecipe: LocalRecipe){
        recipeDatabase.recipeDao().insertOneRecipe(localRecipe)
    }

    suspend fun getAllRecipes(): MutableList<LocalRecipe>{
        return  recipeDatabase.recipeDao().getAllRecipes()
    }

    suspend fun getOneRecipe(id: Int): LocalRecipe {
        return  recipeDatabase.recipeDao().getOneRecipe(id)
    }


}