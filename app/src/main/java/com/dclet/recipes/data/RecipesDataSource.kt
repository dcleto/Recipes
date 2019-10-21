package com.dclet.recipes.data

import com.dclet.recipes.model.DishDetail
import com.dclet.recipes.model.MealPlan
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import java.util.*

interface RecipesDataSource {

    fun getRecipes(category : String?=null, calories : Float?=null, period : String?=null) : Maybe<MealPlan>

    fun getRecipe(id : Long) : Maybe<DishDetail>

    fun saveMealPlan(mealPlan : MealPlan,category: String? = null, calories: Float? = null) : Completable

    fun saveDish(dishDetail: DishDetail) : Completable

}
