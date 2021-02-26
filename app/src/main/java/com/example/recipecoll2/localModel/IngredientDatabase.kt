package com.example.recipecoll2.localModel

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Ingredient::class], version = 1)
abstract class IngredientDatabase : RoomDatabase() {
    abstract fun ingredientsDao(): IngredientDao
}