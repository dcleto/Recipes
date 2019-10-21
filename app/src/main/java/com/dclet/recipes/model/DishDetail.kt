package com.dclet.recipes.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "dish")
class DishDetail(

    @PrimaryKey
    var id : Long,
    var title : String,
    var image : String,
    var readyInMinuts : Int,
    var instructions : String
){
    @Ignore var extendedIngredients : List<Ingredient> = listOf()

}