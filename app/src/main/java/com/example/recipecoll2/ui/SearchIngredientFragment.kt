package com.example.recipecoll2.ui

import android.os.Bundle
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
import com.example.recipecoll2.remoteModel.Ingredient
import com.example.recipecoll2.viewModel.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.mainRecyclerView
import kotlinx.android.synthetic.main.fragment_search_ingredient.*

class SearchIngredientFragment: Fragment() {

    lateinit var navController: NavController
    lateinit var viewModel: RecipeViewModel
    lateinit var adapter:IngredientAdapter
    var ingredients= mutableListOf<Ingredient>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(activity as MainActivity).get(RecipeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        adapter = IngredientAdapter(ingredients, this)
        ingredientRecyclerView.adapter = adapter
        ingredientRecyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.getAllIngredients()

        viewModel.getData()
        viewModel.ingredientsLive.observe(activity as MainActivity, Observer {
            ingredients.clear()
            ingredients.addAll(it)
            ingredientRecyclerView.adapter?.notifyDataSetChanged()
        })
    }

    fun selectIngredient(position: Int) {

        if (ingredients[position].isSelect == 0) {
            ingredients[position].isSelect = 1
        } else {
            ingredients[position].isSelect = 0
        }
        adapter.notifyItemChanged(position)

    }



}