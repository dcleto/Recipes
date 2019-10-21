package com.dclet.recipes.data.source.remote

import com.dclet.recipes.data.RecipesDataSource
import com.dclet.recipes.model.DishDetail
import com.dclet.recipes.model.MealPlan
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class RecipesRemoteDataSource @Inject constructor(var recipesService: RecipesService) : RecipesDataSource {

    override fun getRecipe(id: Long): Maybe<DishDetail> {
        return recipesService.getRecipe(id)
    }

    override fun getRecipes(
        category: String?,
        calories: Float?,
        period: String?
    ): Maybe<MealPlan> {
        return recipesService.getRecipes(category,calories,period)
     }

    override fun saveMealPlan(mealPlan: MealPlan,category: String?,calories: Float?): Completable {
        return Completable.complete()
    }

    override fun saveDish(dishDetail: DishDetail): Completable {
        return Completable.complete()
    }




}