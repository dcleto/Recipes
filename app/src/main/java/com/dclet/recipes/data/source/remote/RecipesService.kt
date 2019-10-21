package com.dclet.recipes.data.source.remote

import com.dclet.recipes.model.DishDetail
import com.dclet.recipes.model.MealPlan
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesService{

    @Headers("x-rapidapi-host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com","x-rapidapi-key: 1cf5cab1e2msh47d14e691f30295p1aadd0jsn87eb7deb2804")
    @GET("mealplans/generate")
    fun getRecipes(@Query("diet") diet :String? = null, @Query("targetCalories") targetCalories : Float?=0f, @Query("timeFrame")  timeFrame : String?=null) : Maybe<MealPlan>

    @Headers("x-rapidapi-host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com","x-rapidapi-key: 1cf5cab1e2msh47d14e691f30295p1aadd0jsn87eb7deb2804")
    @GET("{id}/information")
    fun getRecipe(@Path("id")id : Long) : Maybe<DishDetail>

}
