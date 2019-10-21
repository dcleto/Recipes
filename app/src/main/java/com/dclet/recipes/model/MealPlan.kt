package com.dclet.recipes.model

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation

data class MealPlan (

    @Ignore var nutrients : MealNutrients,

    var meals : List<Dish>
)