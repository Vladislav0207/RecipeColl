package com.example.recipecoll2.remoteModel

import androidx.room.*


data class Recipes(
    val recipes: MutableList<Recipe>
)

data class Recipe (
    @Relation(parentColumn ="id", entityColumn = "recipe_id" )
    var extendedIngredients: List<Ingredient>,
    var id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings:Int,
    val image: String,
    val instructions: String
)
@Entity(tableName = "ingredient")
data class Ingredient(
    val id: Int,
    val image:String,
    val nameClean: String,
    val amount: String,
    val unit: String,
    var recipe_id: Int,
    @PrimaryKey (autoGenerate = true)
    var key : Int=0
)