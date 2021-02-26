package com.example.recipecoll2.localModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class Ingredient(
    @PrimaryKey val id: Int = 0,
    val image:String = "null",
    val nameClean: String = "null",
    val amount: String = "null",
    val unit: String = "null"
)
