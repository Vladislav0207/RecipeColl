package com.example.recipecoll2.localModel

import android.content.Context
import androidx.room.Room

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

    suspend fun insertOneRecipe(localRecipe: LocalRecipe){
        recipeDatabase.recipeDao().insertOneRecipe(localRecipe)
    }

    suspend fun getAllRecipes(): MutableList<LocalRecipe>{
        return  recipeDatabase.recipeDao().getAllRecipes()
    }

    suspend fun getOneRecipe(id: Int): LocalRecipe {
        return  recipeDatabase.recipeDao().getOneRecipe(id)
    }

    suspend fun insertIngredients(localIngredients : MutableList<LocalIngredient>){
        recipeDatabase.ingredientDao().insertIngredients(localIngredients)
    }

    suspend fun getAllIngredients(): MutableList<LocalIngredient>{
       return recipeDatabase.ingredientDao().getAllIngredients()
    }

    suspend fun getRecipeWithIngredients(): List<LocalRecipeWithIngredients>{
        return recipeDatabase.recipeDao().getRecipesWithIngredients()
    }

    suspend fun getOneRecipeWithIngredients(id:Int): LocalRecipeWithIngredients{
        return recipeDatabase.recipeDao().getOneRecipeWithIngredients(id)
    }

    suspend fun getAllIngredientsByRecipeId(recipeId:Int) : MutableList<LocalIngredient>{
        return recipeDatabase.ingredientDao().getAllIngredientsByRecipeId(recipeId)
    }
}