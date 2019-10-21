package com.dclet.recipes.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "meal_plans", indices = [Index(value = ["category", "date","calories"], unique = true)])
class MealPlanItem(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    var category: String,

    @Embedded var nutrients: MealNutrients,

    var date: Long = System.currentTimeMillis(),

    var query_calories : Float? = null

){
    var creation: Long = System.currentTimeMillis()
}