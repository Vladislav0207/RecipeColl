package com.example.recipecoll2.localModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipecoll2.remoteModel.Ingredient

@Dao
interface IngredientDao {
    @Insert
    suspend fun insertAllIngredients(localIngredients : List<Ingredient>)

    @Query("SELECT * FROM ingredient")
    suspend fun getAllIngredients():List<Ingredient>

}