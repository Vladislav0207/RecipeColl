package com.example.recipecoll2.localModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipecoll2.remoteModel.Recipe

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(recipe  : MutableList<Recipe>)
    @Query("SELECT * FROM recipe")
    suspend fun getAllRecipes():MutableList<Recipe>
    @Insert
    suspend fun insertOneRecipe(Recipe: Recipe)
    @Query("SELECT * FROM recipe WHERE id = :id")
    suspend fun getOneRecipe(id: Int) : Recipe

//    @Query("UPDATE recipe SET isFavorite = :isSelected WHERE id = :id")
//    suspend fun updateOnePost(id:Int,isSelected:Int)
//    @Query("SELECT * FROM posts WHERE isFavorite = 1")
//    suspend fun selectFavorites():MutableList<MyPost>

}