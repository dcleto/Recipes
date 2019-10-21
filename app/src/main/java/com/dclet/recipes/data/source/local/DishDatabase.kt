package com.dclet.recipes.data.source.local

import android.content.Context
import androidx.room.*
import com.dclet.recipes.model.*

@Database(entities = [Dish::class, MealPlanItem::class, MealPlanDishJoin::class, DishDetail::class, Ingredient::class, DishIngredientsJoin::class], version = 1, exportSchema = false)
abstract class DishDatabase : RoomDatabase() {

    abstract val dishDao : DishDao

    companion object{

        @Volatile
        private var INSTANCE : DishDatabase? = null

        fun getInstance(context : Context) : DishDatabase {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        DishDatabase::class.java,
                        "dish_database")
                        .fallbackToDestructiveMigration()
                        .build()

            }
            return INSTANCE!!

        }
    }

}