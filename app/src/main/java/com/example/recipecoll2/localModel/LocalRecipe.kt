package com.example.recipecoll2.localModel

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.recipecoll2.remoteModel.Ingredient


@Entity(tableName = "recipe")
data class LocalRecipe (
    @PrimaryKey val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings:Int,
    val image: String,
    val instructions: String,
    var isFavorite : Int
    )