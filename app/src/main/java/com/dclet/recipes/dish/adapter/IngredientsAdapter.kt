package com.dclet.recipes.dish.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dclet.recipes.R
import com.dclet.recipes.model.Ingredient
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientsAdapter(var mData : List<Ingredient>?)  : RecyclerView.Adapter<IngredientsAdapter.IngredientVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientVH {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient,parent,false)
        return IngredientVH(view)
    }

    override fun getItemCount(): Int {
        return mData?.size?:0
    }

    override fun onBindViewHolder(holder: IngredientVH, position: Int) {
        holder.setData(mData?.get(position))
    }

    fun setData(ingredients: List<Ingredient>) {
        mData = ingredients
        notifyDataSetChanged()
    }

    inner class IngredientVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(ingredient: Ingredient?) = itemView.apply {
            if (ingredient == null) return itemView
            tv_name.text = ingredient.name
            Glide.with(iv_picture.context).load("https://spoonacular.com/cdn/ingredients_100x100/${ingredient.image}").into(iv_picture)
        }
    }

}