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
import com.example.recipecoll2.R
import com.example.recipecoll2.viewModel.RecipeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_information.*
import kotlinx.android.synthetic.main.fragment_main.*

class InformationFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var viewModel: RecipeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel= ViewModelProvider(activity as MainActivity).get(RecipeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        Picasso.get().load(viewModel.selectRecipe!!.image).into(imageInf)
        val time = "readyInMinutes: " + viewModel.selectRecipe!!.readyInMinutes.toString()
        val servings = "servings: " + viewModel.selectRecipe!!.servings.toString()
        nameInf.text=viewModel.selectRecipe!!.title
        timeInf.text = time
        servingsInf.text =servings
        var ingredient = "Ingredients:\n"
        viewModel.selectRecipe!!.extendedIngredients.forEach {
           ingredient += it.nameClean + ": " + it.amount + " " + it.unit + "\n"
        }
        ingredientsInf.text=ingredient
        instructionInf.text = viewModel.selectRecipe!!.instructions.replace(
            "</li><li>",
        "\n"
        ).replace(
            "<ol><li>",
            "  "
        ).replace(
            "</li></ol>",
            ""
        )
    }
}