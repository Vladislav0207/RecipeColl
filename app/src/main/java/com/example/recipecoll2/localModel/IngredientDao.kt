package com.example.recipecoll2.localModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IngredientDao {
    @Insert
    suspend fun insertIngredients(ingredient  : MutableList<Ingredient>)
    @Query("SELECT * FROM ingredients")
    suspend fun getAllIngredients():MutableList<Ingredient>
}