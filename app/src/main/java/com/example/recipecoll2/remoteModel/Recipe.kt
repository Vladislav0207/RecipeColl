package com.example.recipecoll2.remoteModel



data class Recipes(
    val recipes: MutableList<Recipe>
)

data class Recipe (
    var extendedIngredients: MutableList<Ingredient>,
    val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings:Int,
    val image: String,
    val instructions: String
)
data class Ingredient(
    val id: Int,
    val image:String,
    val nameClean: String,
    val amount: String,
    val unit: String,
    val recipeId: Int = 0
)