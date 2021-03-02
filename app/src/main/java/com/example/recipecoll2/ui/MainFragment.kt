package com.example.recipecoll2.ui

import android.icu.text.Transliterator
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
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var viewModel: RecipeViewModel
    lateinit var adapter: RecipeAdapter
    var recipes = mutableListOf<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(activity as MainActivity).get(RecipeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()


        viewModel.getData()
        recipes = viewModel.recipeLive.value!!
        adapter = RecipeAdapter(recipes, this)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.recipeLive.observe(activity as MainActivity, Observer {
            recipes.clear()
            recipes.addAll(it)
            Log.d("!!!",recipes.toString())
            mainRecyclerView.adapter?.notifyDataSetChanged()
        })

    }


    fun showRecipe(position: Int) {
        viewModel.showRecipe = recipes[position]
        navController.navigate(R.id.informationFragment)
    }

    fun addFavoriteRecipe(position: Int){
            if (recipes[position].isFavorite == 0) {
                viewModel.updateRecipe(recipes[position].id, 1)
                recipes[position].isFavorite = 1
            } else {
                viewModel.updateRecipe(recipes[position].id,0)
                recipes[position].isFavorite=0
            }
            adapter.notifyItemChanged(position)
        }



    }

