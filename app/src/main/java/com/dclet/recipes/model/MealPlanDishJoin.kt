package com.dclet.recipes.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "meal_dish_join",
    primaryKeys = ["dishId", "mealPlanId"],
    foreignKeys = [
        ForeignKey(
            entity = Dish::class,
            parentColumns = ["id"],
            childColumns = ["dishId"]),
        ForeignKey(entity = MealPlanItem::class,
            parentColumns = ["id"],
        childColumns = ["mealPlanId"])]
)
class MealPlanDishJoin (
    var dishId : Int,
    var mealPlanId : String
)