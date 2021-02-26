package com.example.recipecoll2.remoteModel

import androidx.room.PrimaryKey
import com.example.recipecoll2.localModel.Ingredient

data class AllRecipe (
    var id: Int = 0,
    val title: String = "null",
    val readyInMinutes: Int = 0,
    val servings:Int = 0,
    val image: String = "null",
    val instructions: String,
    val ingredients : MutableList<Ingredient>
)
