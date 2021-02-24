package com.example.recipecoll2.localModel

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipecoll2.remoteModel.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}