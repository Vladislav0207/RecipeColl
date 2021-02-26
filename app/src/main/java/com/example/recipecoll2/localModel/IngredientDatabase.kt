package com.example.recipecoll2.localModel

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Ingredients::class], version = 1)
abstract class IngredientDatabase : RoomDatabase() {
    abstract fun ingredientsDao(): IngredientDao
}