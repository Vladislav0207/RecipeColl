package com.example.recipecoll2.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipecoll2.R
import com.example.recipecoll2.remoteModel.Recipe
import com.example.recipecoll2.viewModel.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_main.*

class FavoriteFragment: Fragment() {
    lateinit var navController: NavController
    lateinit var viewModel: RecipeViewModel
    var favoriteRecipesList = mutableListOf<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(activity as MainActivity).get(RecipeViewModel::class.java)


        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        favoriteRecipesList.clear()
        Log.d("!!!FF",viewModel.favoriteList.toString())
        favoriteRecipesList.addAll(viewModel.favoriteList)

        val adapter = RecipeFavoriteAdapter(favoriteRecipesList, this)
        favoriteRecyclerView.adapter = adapter
        favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        favoriteRecyclerView.adapter?.notifyDataSetChanged()


    }


    fun showRecipeInFavorite(position: Int) {
        viewModel.showRecipe = favoriteRecipesList[position]
        navController.navigate(R.id.informationFragment)
    }


    fun changeFavoriteRecipeInFavorite(position: Int){
            viewModel.updateOutFavorites(favoriteRecipesList[position].id)
        favoriteRecipesList.remove(favoriteRecipesList[position])
        favoriteRecyclerView.adapter!!.notifyDataSetChanged()
    }

}