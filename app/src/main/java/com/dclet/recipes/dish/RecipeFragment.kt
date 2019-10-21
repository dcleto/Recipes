package com.dclet.recipes.dish


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dclet.recipes.R
import com.dclet.recipes.model.DishDetail
import kotlinx.android.synthetic.main.fragment_recipe.*

/**
 * A simple [Fragment] subclass.
 */
class RecipeFragment : DishContract.View, Fragment() {

    override var presenter: DishContract.Presenter
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_recipe, container, false)
        return rootView
    }

    override fun setDetails(detail: DishDetail) {
        tv_preparation.setText(detail.instructions)
        var st = StringBuilder ()
        detail.extendedIngredients.forEach { ingredient ->
            st.append(ingredient.original)
            st.append('\n')
        }
        tv_ingredients.text = st.toString()
     }

}
