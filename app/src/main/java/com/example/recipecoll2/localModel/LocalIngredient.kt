package com.example.recipecoll2.localModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class LocalIngredient(
    @PrimaryKey val ingredientId: Int,
    val IngredientImage:String,
    val nameClean: String,
    val amount: String,
    val unit: String,
    val recipeId: Int
)
