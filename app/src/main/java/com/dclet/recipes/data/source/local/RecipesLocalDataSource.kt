package com.dclet.recipes.data.source.local

import com.dclet.recipes.data.RecipesDataSource
import com.dclet.recipes.model.*
import com.dclet.recipes.utils.DateUtils
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class  RecipesLocalDataSource @Inject constructor(var db : DishDao) : RecipesDataSource{

    override fun getRecipes(
        category: String?,
        calories: Float?,
        period: String?
    ): Maybe<MealPlan> {
        var getMealPlan = if(calories == null)
                            db.getMealPlan(category?:"all",DateUtils.getMidNigthInMilis())
                         else
                            db.getMealPlan(category?:"all",calories,DateUtils.getMidNigthInMilis())

        return  getMealPlan
             .flatMap{ meals-> db.getDishesForMealPlans(meals.id).firstElement().map { MealPlan(meals.nutrients,it.toList()) }}
     }

    override fun getRecipe(id: Long): Maybe<DishDetail> {
        var getDish = db.getDishDetails(id)
        var getIngredients =  db.getDishIngredients(id)
        return Maybe.zip(getDish,getIngredients, BiFunction{ dish : DishDetail, ingredients : Array<Ingredient> -> dish?.extendedIngredients = ingredients.toList(); dish})
    }

    override fun saveMealPlan(mealPlan: MealPlan, category: String?,calories: Float?): Completable {
       return db.insert(mealPlan.meals)

           .andThen(db.insertMealPlan(MealPlanItem(0,category!!,mealPlan.nutrients,DateUtils.getMidNigthInMilis(),query_calories =  calories)))

           .flatMapCompletable{x->
               var join  = mealPlan.meals.map { MealPlanDishJoin(it.id.toInt(), x.toString())}
               db.insertMealPlanDishes(
                   join
               )

           }
     }

    override fun saveDish(dishDetail: DishDetail): Completable{
        var insert = db.insertDishWithDetails(dishDetail).ignoreElement()
        var insertIngredients = db.insertIngredients(dishDetail.extendedIngredients)
        var insertJoin = db.insertDishIngredients(dishDetail.extendedIngredients.map { DishIngredientsJoin(dishDetail.id,it.id) })

         return Completable.merge(listOf(insert,insertIngredients,insertJoin))
    }


}