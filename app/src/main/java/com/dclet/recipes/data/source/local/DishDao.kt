package com.dclet.recipes.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import androidx.room.FtsOptions.Order
import com.dclet.recipes.model.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*


@Dao
interface DishDao {

    @Insert
    fun insert(dish : Dish)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dish : List<Dish>) : Completable

    @Insert
    fun insertDishWithDetails(dish : DishDetail) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIngredients(ingredients : List<Ingredient>) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDishIngredients( dishJIngredientsJoin: DishIngredientsJoin) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDishIngredients( dishIngredientsJoin: List<DishIngredientsJoin>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMealPlan( mealPlan: MealPlanItem) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMealPlanDishes( assignedRecipe: MealPlanDishJoin)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMealPlanDishes( assignedRecipe: List<MealPlanDishJoin>) : Completable

    @Query("SELECT * FROM meal_plan_dish")
    fun getDishes() : Observable<List<Dish>>

    @Query("SELECT * FROM meal_plan_dish where id = :id")
    fun getDish(id : Long) : Observable<Dish?>

    @Query("""
           SELECT * FROM dish_ingredients_join
           INNER JOIN ingredients
           ON dish_ingredients_join.ingredientId = ingredients.id
           WHERE dish_ingredients_join.dishId=:dishId
           """)
    fun getDishIngredients(dishId: Long): Maybe<Array<Ingredient>>

    @Query("SELECT * FROM dish where id = :id")
    fun getDishDetails(id : Long) : Maybe<DishDetail>

    @Query("SELECT * FROM meal_plans where category = :category and date = :date LIMIT 1")
    fun getMealPlan(category: String,  date : Long) : Maybe<MealPlanItem>

    @Query("SELECT * FROM meal_plans where category = :category and date = :date and query_calories = :calories LIMIT 1 ")
    fun getMealPlan(category: String, calories: Float, date : Long) : Maybe<MealPlanItem>


    @Query("""
           SELECT * FROM meal_plan_dish
           INNER JOIN meal_dish_join
           ON meal_plan_dish.id = meal_dish_join.dishId
           WHERE meal_dish_join.mealPlanId=:mealPlanId
           """)
    fun getDishesForMealPlans(mealPlanId: Long): Observable<Array<Dish>>

}