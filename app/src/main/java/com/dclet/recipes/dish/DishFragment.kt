package com.dclet.recipes.dish


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.dclet.recipes.R
import com.dclet.recipes.dish.adapter.DishPageAdapter
import com.dclet.recipes.model.DishDetail
import com.dclet.recipes.model.Ingredient
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_dish.*
import kotlinx.android.synthetic.main.fragment_dish.view.*
import kotlinx.android.synthetic.main.fragment_dish.view.iv_picture
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class DishFragment @Inject constructor() : DishContract.View, DaggerFragment() {

    var subviews : MutableList<DishContract.View> = mutableListOf()

    @Inject
    override lateinit var presenter : DishContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_dish, container, false)
        if (fragmentManager == null) return rootView

        var ingredients = IngredientsFragment()
        var recipe = RecipeFragment()
        subviews = mutableListOf(ingredients,recipe)

        rootView.pager.adapter = DishPageAdapter(fragmentManager!!, listOf(ingredients,recipe), listOf(getString(
                    R.string.ingredients),getString(R.string.recipe)))
        rootView.tab_layout.setupWithViewPager(rootView.pager)
        rootView.tab_layout.getTabAt(0)?.setIcon(R.drawable.ic_harvest)
        rootView.tab_layout.getTabAt(1)?.setIcon(R.drawable.ic_mortar)
        return rootView
    }

    override fun setDetails(detail: DishDetail) {
        Glide.with(activity!!).load(detail.image).into(iv_picture)
        activity?.title = detail.title
        subviews.forEach{
            it.setDetails(detail)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

}
