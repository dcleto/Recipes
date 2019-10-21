package com.dclet.recipes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_plan_dish")
data class Dish(

    @PrimaryKey
    var id: Long,

    var title : String,

    var readyInMinutes : Int)