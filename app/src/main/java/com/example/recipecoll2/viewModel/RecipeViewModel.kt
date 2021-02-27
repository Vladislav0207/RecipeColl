package com.example.recipecoll2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipecoll2.localModel.LocalRecipe
import com.example.recipecoll2.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel (val repository: Repository) : ViewModel() {
    val scope = CoroutineScope(Dispatchers.IO)
    val localRecipeLive : MutableLiveData<MutableList<LocalRecipe>> by lazy {
        MutableLiveData<MutableList<LocalRecipe>>()
    }

    fun getData() {
        scope.launch {
            val data = repository.getData()
            localRecipeLive.postValue(data)
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
//    fun getOnePost(id :Int){
//        scope.launch {
//            val recipe = repository.getOneRecipe(id)
//            val recipeList = mutableListOf<Recipe>()
//            recipeList.add(recipe)
//            recipeLive.postValue(recipeList)
//        }
//    }
}