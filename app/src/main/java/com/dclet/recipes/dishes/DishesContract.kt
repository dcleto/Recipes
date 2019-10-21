package com.dclet.recipes.dishes

import com.dclet.recipes.BasePresenter
import com.dclet.recipes.BaseView
import com.dclet.recipes.model.Dish
import com.dclet.recipes.model.FoodCategory

class DishesContract {
    interface Presenter : BasePresenter<View>{

        fun loadRecipes(category : String? = null, calories : Float? = null, period : String? = null)

        fun loadRecipes(categoryPosition : Int)

        fun openRecipe(recipeId: Long)

        fun setCategoryPosition(categoryPosition : Int)

        fun getCategoryPosition() : Int

        fun updateCalories()

    }

    interface View : BaseView<Presenter>{

        fun setCategoriesTabs(categories : List<FoodCategory>)

        fun showRecipes(recipes : List<Dish>)

        fun showRecipes(recipeId: Long)

        fun showLoading(show : Boolean)

        fun showCalories(calories : Float?)

        fun showCaloriesPicker(onCaloriesSelected : (Float?)->Unit)
    }
}