package com.example.recipecoll2.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipecoll2.remoteModel.Ingredient
import com.example.recipecoll2.remoteModel.Recipe
import com.example.recipecoll2.repository.Repository
import kotlinx.coroutines.*

class RecipeViewModel (val repository: Repository) : ViewModel() {

    var showRecipe : Recipe? = null

    val ingredientsLive : MutableLiveData<MutableSet<Ingredient>> by lazy {
        MutableLiveData<MutableSet<Ingredient>>()
    }
    val scope = CoroutineScope(Dispatchers.IO)


    val favoriteList = mutableListOf<Recipe>()

    val recipeLive : MutableLiveData<MutableList<Recipe>> by lazy {
        MutableLiveData<MutableList<Recipe>>()
    }

    val recipeResultLive : MutableLiveData<MutableList<Recipe>> by lazy {
        MutableLiveData<MutableList<Recipe>>()
    }

    var listOfNamesIngredientSelected = mutableListOf<String>()

    fun getData() {
        scope.launch {
            val data = repository.getData()
            recipeLive.postValue(data)
        }
   }

    fun updateRecipe(recipeId:Int,isSelected:Int){
        scope.launch {
            repository.updateRecipe(recipeId,isSelected)
        }
    }

    fun updateInFavorites(position :Int){
        scope.launch {
            updateRecipe(recipeLive.value!![position].id,1)
            recipeLive.value!![position].isFavorite = 1
            favoriteList.add(recipeLive.value!![position])
        }
    }

    fun updateOutFavorites(recipeId: Int){
        scope.launch {
            updateRecipe(recipeId,0)
            favoriteList.removeIf{it.id == recipeId}
        }
    }

    fun getAllIngredients() {
        scope.launch {
            val data = repository.getAllIngredients().toMutableSet()
            ingredientsLive.postValue(data)
        }
    }

    fun searchByIngredient(listOfNames: MutableList<String>){
        scope.launch {
            val data = repository.searchByIngredient(listOfNames)
            recipeResultLive.postValue(data)
        }
    }

    fun getFavorites() {
        CoroutineScope(Dispatchers.Main).launch  {
            val data = repository.getFavorites()
            Log.d("!!!RRR",repository.getFavorites().toString())
            favoriteList.clear()
            favoriteList.addAll(data)
        }
    }
}