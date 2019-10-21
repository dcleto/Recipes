package com.dclet.recipes.dish


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.dclet.recipes.R
import com.dclet.recipes.dish.adapter.IngredientsAdapter
import com.dclet.recipes.model.DishDetail
import kotlinx.android.synthetic.main.fragment_ingredients.view.*

/**
 * A simple [Fragment] subclass.
 */
class IngredientsFragment: DishContract.View,Fragment() {

    override var presenter: DishContract.Presenter
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    lateinit var adapter : IngredientsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_ingredients, container, false)
        adapter = IngredientsAdapter(null)
        rootView.recyler_ingredients.adapter = adapter
        rootView.recyler_ingredients.layoutManager = GridLayoutManager(activity,3)
        return rootView
    }

    override fun setDetails(detail: DishDetail) {
        adapter.setData(detail.extendedIngredients)
     }


}
