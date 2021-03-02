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

class ResultSearchFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var viewModel: RecipeViewModel
    lateinit var recipeList: MutableList<Recipe>
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


        navController = findNavController()

        recipeList= viewModel.recipeLive.value!!
        val listOfNames= viewModel.listOfSelectedIngredient



        for (i in 0 until  recipeList.size){
//            recipeList[i]
           var h =0
            for (j in 0 until  recipeList[i].extendedIngredients.size) {
//                recipeList[i].extendedIngredients[j]
                for (k in 0 until  listOfNames.size){
                    if (recipeList[i].extendedIngredients[j].nameClean == listOfNames[k]){
                        h+=1
                    }
                }
            }
            if (h == listOfNames.size){
               resultList.add(recipeList[i])
            }
        }


        val adapter = RecipeAdapter(resultList, MainFragment())
        resultSearchRecyclerView.adapter = adapter
        resultSearchRecyclerView.layoutManager = LinearLayoutManager(activity)




    }
}