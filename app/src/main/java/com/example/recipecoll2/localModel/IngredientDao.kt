package com.example.recipecoll2.localModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IngredientDao {
    @Insert
    suspend fun insertIngredients(localIngredients  : MutableList<LocalIngredient>)

    @Query("SELECT * FROM ingredients")
    suspend fun getAllIngredients():MutableList<LocalIngredient>

    @Query("select * from ingredients where recipeId = :recipeId" )
    suspend fun getAllIngredientsByRecipeId(recipeId : Int):MutableList<LocalIngredient>
}