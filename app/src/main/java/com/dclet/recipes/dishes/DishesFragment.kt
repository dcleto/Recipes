package com.dclet.recipes.dishes


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager

import com.dclet.recipes.R
import com.dclet.recipes.di.ActivityScoped
import com.dclet.recipes.dish.DishActivity
import com.dclet.recipes.dishes.adapter.DishesRecyclerAdapter
import com.dclet.recipes.model.Dish
import com.dclet.recipes.model.FoodCategory
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_dishes.*
import kotlinx.android.synthetic.main.fragment_dishes.tabs
import kotlinx.android.synthetic.main.fragment_dishes.view.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
@ActivityScoped
class DishesFragment  @Inject constructor(): DishesContract.View, DaggerFragment() {

    @Inject
    override lateinit var presenter: DishesContract.Presenter

    lateinit var adapter : DishesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView =  inflater.inflate(R.layout.fragment_dishes, container, false)

        rootView.iv_pick_calories.setOnClickListener{presenter.updateCalories()}
        adapter = DishesRecyclerAdapter(null) { recipeId ->
            presenter.openRecipe(recipeId)
        }

        rootView.recycler_dishes.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        rootView.run {
            adapter = DishesRecyclerAdapter(null) { recipeId ->
                presenter.openRecipe(recipeId)
            }

            recycler_dishes.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            tabs.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    presenter.loadRecipes(tab.position)
                 }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }

            })

            recycler_dishes.adapter = adapter

        }

        return rootView
    }

    override fun setCategoriesTabs(categories: List<FoodCategory>) {
        if(view?.tabs?.tabCount?:0 > 0) return
        categories.forEach{ category ->  tabs.addTab(tabs.newTab()?.setText(category.name))}
        view?.tabs?.tabMode = TabLayout.MODE_SCROLLABLE
    }

    override fun showLoading(show: Boolean) {
        if(show){
            progress.visibility = View.VISIBLE
            recycler_dishes.visibility = View.GONE
        }
        else{
            progress.visibility = View.GONE
            recycler_dishes.visibility = View.VISIBLE
        }
    }

    override fun showRecipe(recipes: List<Dish>) {
        adapter.setData(recipes)
    }

    override fun showRecipe(recipeId: Long) {
        var intent = Intent(activity, DishActivity::class.java)
        intent.putExtra(DishActivity.EXTRA_DISH_ID,recipeId)
        startActivity(intent)
    }

    override fun showCaloriesPicker(onCaloriesSelected: (Float?) -> Unit) {
        val dialog = Dialog(activity)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_calories_picker)

        val etCalories = dialog.findViewById<EditText>(R.id.et_calories)
        val btnAccept = dialog.findViewById<Button>(R.id.btn_accept)
        val btnCancel = dialog.findViewById<Button>(R.id.btn_cancel)
        val btnClear = dialog.findViewById<Button>(R.id.btn_clear)
        btnAccept.setOnClickListener{
            var calories = etCalories.text.toString()
            onCaloriesSelected(if(calories.isEmpty()) 0f else calories.toFloat())
            dialog.dismiss()}
        btnCancel.setOnClickListener{ dialog.dismiss() }
        btnClear.setOnClickListener{ onCaloriesSelected(-1f);dialog.dismiss() }

        dialog.show()
    }

    override fun showCalories(calories: Float?) {
        if(calories != null && calories > 0){
            view?.iv_pick_calories?.setImageResource(R.drawable.ic_fire_red)
            tv_meal_plan.text = "Meal Plan ($calories calories)"
        }
        else{
            view?.iv_pick_calories?.setImageResource(R.drawable.ic_fire)
            tv_meal_plan.text = "Meal Plan"

        }
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

}
