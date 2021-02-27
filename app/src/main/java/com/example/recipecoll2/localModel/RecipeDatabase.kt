package com.example.recipecoll2.localModel

import androidx.room.Database
import androidx.room.RoomDatabase
//create database
@Database(entities = [LocalRecipe::class, LocalIngredient::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao
}