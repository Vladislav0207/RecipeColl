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
import com.example.recipecoll2.remoteModel.Recipe
import com.example.recipecoll2.repository.IngredientView
import com.example.recipecoll2.viewModel.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.mainRecyclerView
import kotlinx.android.synthetic.main.fragment_search_ingredient.*

class SearchIngredientFragment: Fragment() {

    lateinit var navController: NavController
    lateinit var viewModel: RecipeViewModel
    lateinit var adapter:IngredientAdapter
    var ingredients= mutableListOf<IngredientView>()


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
        viewModel.getAllIngredientsView()
        ingredients.addAll(viewModel.ingredientsView.sortedBy { it.name })
        adapter = IngredientAdapter(ingredients, this)
        ingredientRecyclerView.adapter = adapter
        ingredientRecyclerView.layoutManager = LinearLayoutManager(this.context)
        ingredientRecyclerView.adapter?.notifyDataSetChanged()




        btnResultSearch.setOnClickListener {
            //create list of names
            val listNameIngredientsSelect : MutableList<String> = ingredients.filter { it.isSelect }.
                mapTo(mutableListOf<String>())
                {
                    it.name
                }

            viewModel.listOfNamesIngredientSelected = listNameIngredientsSelect

            viewModel.searchByIngredient()


            navController.navigate(R.id.resultSearchFragment)

        }


    }



    fun selectIngredient(position: Int) {

        ingredients[position].isSelect = !ingredients[position].isSelect
        adapter.notifyItemChanged(position)

    }


}
