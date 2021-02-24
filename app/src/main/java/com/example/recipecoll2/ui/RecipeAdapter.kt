package com.example.recipecoll2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipecoll2.R
import com.example.recipecoll2.remoteModel.Recipe

class RecipeAdapter (val recipes: MutableList<Recipe>, val fragment: MainFragment):
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.nameRecipe)
        val ingredients = itemView.findViewById<TextView>(R.id.ingredientsRecipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item,parent,false)
        val holder = RecipeViewHolder(itemView)

        return holder
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.name.text=recipes[position].title
        var body : String? = null
        for (element in recipes[position].extendedIngredients) {
            body += "${element.nameClean}, "
        }
        holder.ingredients.text=body
    }
}