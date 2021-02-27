package com.example.recipecoll2.localModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(tableName = "recipe")
data class LocalRecipe(
    @PrimaryKey val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings:Int,
    val image: String,
    val instructions: String
)
class LocalRecipeWithIngredients(
    val LocalRecipe_id: Int,
    val LocalRecipe_title: String,
    val LocalRecipe_readyInMinutes: Int,
    val LocalRecipe_servings:Int,
    val LocalRecipe_image: String,
    val LocalRecipe_instructions: String,
    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val localIngredient: List<LocalIngredient>
)

