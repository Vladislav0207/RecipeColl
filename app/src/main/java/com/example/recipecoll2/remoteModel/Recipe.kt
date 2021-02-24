package com.example.recipecoll2.remoteModel

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Recipes(
    val recipes: MutableList<Recipe>
)


@Entity(tableName = "recipe")
data class Recipe(
//    val extendedIngredients: List<ExtendedIngredients>?,
    @PrimaryKey val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings:Int,
    val image: String,
//    val dishTypes: List<String>?,
    val instructions: String
)


data class ExtendedIngredients(
    val id: Int,
    val image:String,
    val nameClean: String,
    val amount: String,
    val unit: String
)
