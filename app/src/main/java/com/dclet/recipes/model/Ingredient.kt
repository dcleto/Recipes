package com.dclet.recipes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
class Ingredient(

    @PrimaryKey
    var id : Long = 0L,

    var name : String,

    var amount: Float = 0f,

    var unit : String? = null,

    var unitShort : String? = null,

    var image : String,

    var original : String)