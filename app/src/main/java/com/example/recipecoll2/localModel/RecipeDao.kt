package com.example.recipecoll2.localModel

import androidx.room.*
import com.example.recipecoll2.remoteModel.Recipe

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(Recipe  : MutableList<LocalRecipe>)

//    @Query("SELECT * FROM recipe")
//    suspend fun getAllRecipes():MutableList<Recipe>
//
//
//    @Insert
//    suspend fun insertOneRecipe(Recipe: Recipe)
//
//    @Query("SELECT * FROM recipe WHERE id = :id")
//    suspend fun getOneRecipe(id: Int) : Recipe

    @Query("UPDATE recipe SET isFavorite = :isSelected WHERE id = :id")
    suspend fun updateRecipe(id:Int,isSelected:Int)

    @Transaction
    @Query("select id, title, readyInMinutes, servings, image, instructions, isFavorite from recipe")
    suspend fun getAllRecipe() : List<Recipe>


//    @Query("UPDATE recipe SET isFavorite = :isSelected WHERE id = :id")
//    suspend fun updateOnePost(id:Int,isSelected:Int)
//    @Query("SELECT * FROM posts WHERE isFavorite = 1")
//    suspend fun selectFavorites():MutableList<MyPost>

}