package com.example.recipecoll2.localModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(localRecipe  : MutableList<LocalRecipe>)

    @Query("SELECT * FROM recipe")
    suspend fun getAllRecipes():MutableList<LocalRecipe>

    @Insert
    suspend fun insertOneRecipe(LocalRecipe: LocalRecipe)

    @Query("SELECT * FROM recipe WHERE id = :id")
    suspend fun getOneRecipe(id: Int) : LocalRecipe

    @Transaction
    @Query("select * from recipe")
    suspend fun getRecipesWithIngredients():List<LocalRecipeWithIngredients>

    @Transaction
    @Query("select * from recipe where id=:id ")
    suspend fun getOneRecipeWithIngredients(id: Int):LocalRecipeWithIngredients

//    @Query("UPDATE recipe SET isFavorite = :isSelected WHERE id = :id")
//    suspend fun updateOnePost(id:Int,isSelected:Int)
//    @Query("SELECT * FROM posts WHERE isFavorite = 1")
//    suspend fun selectFavorites():MutableList<MyPost>

}