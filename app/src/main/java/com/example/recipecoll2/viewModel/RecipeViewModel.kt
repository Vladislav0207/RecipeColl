package com.example.recipecoll2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipecoll2.remoteModel.Recipe
import com.example.recipecoll2.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel (val repository: Repository) : ViewModel() {

    var showRecipe : Recipe? = null
    val scope = CoroutineScope(Dispatchers.IO)
    val recipeLive : MutableLiveData<MutableList<Recipe>> by lazy {
        MutableLiveData<MutableList<Recipe>>()
    }

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

//    fun saveData(){
//        scope.launch {
//            repository.saveData(recipeLive.value!!)
//        }
//    }
//
//    fun saveOneRecipe (recipe: Recipe){
//        scope.launch {
//            repository.saveOneRecipe(recipe)
//            recipeLive.value!!.add(recipe)
//        }
//    }
//
//    fun getOneRecipe(id :Int) {
//        scope.launch {
//            val recipe = repository.getOneRecipe(id)
//            val recipeList = mutableListOf<Recipe>()
//            recipeList.add(recipe)
//            recipeLive.postValue(recipeList)
//        }
//    }
}