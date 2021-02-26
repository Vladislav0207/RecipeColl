package com.example.recipecoll2.remoteModel

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Recipes(
    val recipes: MutableList<Recipe>
)


@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey val id: Int = 0,
    val title: String = "null",
    val readyInMinutes: Int = 0,
    val servings:Int = 0,
    val image: String = "null",
    val instructions: String
)
