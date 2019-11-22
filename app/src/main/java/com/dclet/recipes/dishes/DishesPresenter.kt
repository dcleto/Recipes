package com.dclet.recipes.dishes

import android.util.Log
import com.dclet.recipes.data.source.RecipesRepository
import com.dclet.recipes.di.ActivityScoped
import com.dclet.recipes.model.FoodCategory
import com.dclet.recipes.utils.Preferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScoped
class DishesPresenter @Inject constructor(var repo : RecipesRepository, var preferences: Preferences) : DishesContract.Presenter{

    var foodCategories : List<FoodCategory>

    var currentCategory : String = "all"

    var currentPosition : Int = 0

    var currentCalories : Float? = null

    var view : DishesContract.View? = null

    init {
        foodCategories = listOf(
            FoodCategory("All",null),
            FoodCategory("Keto","keto"),
            FoodCategory("Paleo","paleo"),
            FoodCategory("Vegetarian","vegetarian"),
            FoodCategory("Vegan","vegan")
            )
        currentCalories = preferences.getCalories()
    }

    override fun loadRecipes(category: String?, calories: Float?, period: String?){
        view?.showLoading(true)
        var disposable = repo
            .getRecipes(category?:"all",calories,"day")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showLoading(false)
                view?.showRecipe(it.meals)
            },{ err->
                view?.showLoading(false)
                Log.e("DishesPresenter ",err.message)
            })
    }

    override fun loadRecipes(categoryPosition: Int) {
        currentPosition = categoryPosition
        currentCategory = foodCategories[categoryPosition].id.toString()
        var calories = if(currentCalories!= null ) currentCalories else null
        loadRecipes(currentCategory,calories)
    }

    override fun openRecipe(recipeId: Long) {
        view?.showRecipe(recipeId)
    }

    override fun attach(v: DishesContract.View) {
        view = v
        view?.setCategoriesTabs(foodCategories)
        view?.showCalories(currentCalories)
    }

    override fun setCategoryPosition(categoryPosition: Int) {
        currentPosition = categoryPosition
        currentCategory = foodCategories[categoryPosition].id.toString()
        loadRecipes(currentPosition)
    }

    override fun getCategoryPosition(): Int {
        return currentPosition
    }

    override fun updateCalories() {
       view?.showCaloriesPicker {calories->
           preferences.setCalories(calories)
           currentCalories = calories
           view?.showCalories(calories)
           loadRecipes(currentCategory,calories,"day")
       }
    }
    override fun dettach() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}