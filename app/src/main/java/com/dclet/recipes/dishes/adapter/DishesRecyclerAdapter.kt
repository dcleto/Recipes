package com.dclet.recipes.dishes.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dclet.recipes.R
import com.dclet.recipes.model.Dish
import kotlinx.android.synthetic.main.item_dish_card.view.*

class DishesRecyclerAdapter(var dishes : List<Dish>? , var onclickListener : (dishId : Long ) -> Unit) : RecyclerView.Adapter<DishesRecyclerAdapter.DishVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishVH {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.item_dish_card,parent,false)
        return DishVH(view)
    }

    override fun getItemCount(): Int {
        return dishes?.size?:0
    }

    override fun onBindViewHolder(dishVH: DishVH, position: Int) {
        dishVH.setDish(dishes?.get(position))
    }

    fun setData(data : List<Dish>?){
        dishes = data
        notifyDataSetChanged()
    }

    inner class DishVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setDish(dish: Dish?) = itemView.apply {
            if (dish == null) return this
            tv_title.setText(dish.title)
            tv_ready_in_minuts.setText("${dish.readyInMinutes} minutes")
            this.setOnClickListener{onclickListener(dish.id)}
            Glide.with(this)
                .load("https://spoonacular.com/recipeImages/${dish.id}-556x370.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_picture)
        }

    }
}