package com.example.recipecoll2.remoteModel

import androidx.room.Entity

@Entity(tableName = "Ingredients")
data class Ingredients(
    val ingredients : MutableList<Ingredient>
)
data class Ingredient(
    val id: Int = 0,
    val image:String = "null",
    val nameClean: String = "null",
    val amount: String = "null",
    val unit: String = "null"
)
