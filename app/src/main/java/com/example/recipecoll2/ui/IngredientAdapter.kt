package com.example.recipecoll2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipecoll2.R
import com.example.recipecoll2.remoteModel.Ingredient
import com.example.recipecoll2.remoteModel.Recipe
import com.example.recipecoll2.repository.IngredientView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.ingredient_item.view.*
import kotlinx.android.synthetic.main.recipe_item.view.*

class IngredientAdapter (val ingredients: MutableList<IngredientView>, val fragment: SearchIngredientFragment):
    RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {
    class IngredientViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.nameSearchIngredient)
        val image = itemView.findViewById<ImageView>(R.id.imageSearchIngredient)
        val imageSelect = itemView.findViewById<ImageView>(R.id.imageSelectSearchIngredient)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item,parent,false)
        val holder = IngredientViewHolder(itemView)


        holder.itemView.imageSelectSearchIngredient.setOnClickListener {
            fragment.selectIngredient(holder.adapterPosition)
        }

        return holder
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.name.text= ingredients[position].name
        if (ingredients[position].isSelect){
            holder.imageSelect.setImageResource(R.drawable.ic_baseline_check_box_24)
        }
        else{
            holder.imageSelect.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24)
        }
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}