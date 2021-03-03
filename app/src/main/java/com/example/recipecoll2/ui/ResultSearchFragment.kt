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
import com.example.recipecoll2.remoteModel.Recipe
import com.example.recipecoll2.viewModel.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_result_search.*
import kotlinx.android.synthetic.main.fragment_search_ingredient.*

class ResultSearchFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var viewModel: RecipeViewModel
     var resultList = mutableListOf<Recipe>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(activity as MainActivity).get(RecipeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_result_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultList.clear()

        navController = findNavController()


        resultList.addAll(viewModel.recipeResult)
        val adapter = RecipeAdapter(resultList, MainFragment())
        resultSearchRecyclerView.adapter = adapter
        resultSearchRecyclerView.layoutManager = LinearLayoutManager(this.context)
        resultSearchRecyclerView.adapter?.notifyDataSetChanged()



    }
}