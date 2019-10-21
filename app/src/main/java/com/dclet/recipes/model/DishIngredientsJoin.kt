package com.dclet.recipes.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "dish_ingredients_join",
    primaryKeys = ["dishId", "ingredientId"],
    foreignKeys = [
        ForeignKey(
            entity = DishDetail::class,
            parentColumns = ["id"],
            childColumns = ["dishId"]),
        ForeignKey(entity = Ingredient::class,
            parentColumns = ["id"],
        childColumns = ["ingredientId"])]
)
class DishIngredientsJoin (
    var dishId : Long,
    var ingredientId : Long
)