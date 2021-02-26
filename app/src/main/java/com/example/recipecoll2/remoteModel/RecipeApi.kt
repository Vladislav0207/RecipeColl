package com.example.recipecoll2.remoteModel

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/"
const val KEY ="11ce03dfb8msh1b2f22da42cea2fp153590jsnaaae33be1acd"
const val HOST ="spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"


interface ApiService {

    @GET("random")
    suspend fun getRecipes(
        @Header("x-rapidapi-key") key: String,
        @Header("x-rapidapi-host") host: String,
        @Query("number") number: Int
    ): Recipe

    @GET("{recipeId}/ingredientWidget.json")
    suspend fun getRecipeIngredients(
        @Header("x-rapidapi-key") key: String,
        @Header("x-rapidapi-host") host: String,
        @Path("recipeId") recipeId : Int) : Ingredients



//    @GET("https://openweathermap.org/img/w/{imgId}.png")
//    suspend fun getIcon(
//        @Path("imgId") imgId:String) : ResponseBody

    companion object Factory {
        fun create(): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}