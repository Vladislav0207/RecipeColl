package com.example.recipecoll2.localModel

import androidx.room.Entity
import androidx.room.PrimaryKey




@Entity(tableName = "recipe")
data class LocalRecipe(
    @PrimaryKey val id: Int = 0,
    val title: String = "null",
    val readyInMinutes: Int = 0,
    val servings:Int = 0,
    val image: String = "null",
    val instructions: String
)

