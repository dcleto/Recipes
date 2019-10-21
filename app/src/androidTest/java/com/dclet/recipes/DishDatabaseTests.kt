package com.dclet.recipes

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dclet.recipes.data.source.local.DishDao
import com.dclet.recipes.data.source.local.DishDatabase
import com.dclet.recipes.model.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception


@RunWith(AndroidJUnit4::class)
class DishDatabaseTests{

    private lateinit var dishDao : DishDao
    private lateinit var db : DishDatabase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context,
            DishDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dishDao = db.dishDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }


    @Test
    @Throws(Exception::class)
    fun insertAndGetDish(){

        val dish = Dish(1,"Dish1",15)
        dishDao.insert(dish)
        dishDao.getDish(1)
            .test()
            .awaitCount(1)
            .assertValue{it.id == 1L}

    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetMealPlan(){
        var mealPlan = MealPlanItem(0,"keto",MealNutrients(10f,20f,23f,34f))
        mealPlan.date = 10L
        dishDao.insertMealPlan(mealPlan).subscribe()
        dishDao.getMealPlan("keto",10L)
            .test()
            .awaitCount(1)
            .assertValue{it.id == 1L}

    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetMealPlanWithDishes(){
        var mealPlan = MealPlanItem(0,"keto",MealNutrients(10f,20f,23f,34f))
        dishDao.insertMealPlan(mealPlan).subscribe()

        val dish = Dish(1,"Dish1",15)
        dishDao.insert(dish)

        dishDao.insertMealPlanDishes(MealPlanDishJoin(1,"1"))

        dishDao.getDishesForMealPlans(1)
            .test()
            .awaitCount(1)
            .assertValue{it.first().title == "Dish1"}

    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetDishDetail(){
        var dishDetail = DishDetail(1L,"Plato1","",2,"Se menea" )

        dishDao.insertDishWithDetails(dishDetail)

        dishDao.getDishDetails(1)
            .test()
            .awaitCount(1)
            .assertValue{it.title == "Plato1"}

    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetDishDetailWithIngredients(){
        var dishDetail = DishDetail(1L,"Plato1","",2,"Se menea" )
        var ingredients : List<Ingredient> = listOf(
            Ingredient(1,"Azucar",2f,"g",null,"",""),
            Ingredient(2,"Arroz",2f,"g",null,"",""),
            Ingredient(3,"Pan",2f,"g",null,"",""),
            Ingredient(4,"Yuca",2f,"g",null,"",""),
            Ingredient(5,"Platano",2f,"g",null,"","")
        )
        dishDao.insertDishWithDetails(dishDetail)

        dishDao.insertIngredients(ingredients)

        dishDao.insertDishIngredients(ingredients.map { DishIngredientsJoin(dishDetail.id, it.id) })

        dishDao.getDishDetails(1)
            .test()
            .awaitCount(1)
            .assertValue{it.title == "Plato1"}

        dishDao.getDishIngredients(1)
            .test()
            .awaitCount(1)
            .assertValue{it.size == 5}

    }


}