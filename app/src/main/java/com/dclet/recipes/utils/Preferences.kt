package com.dclet.recipes.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {

    private lateinit var preferences: SharedPreferences

    private  val CALORIES = "calories"

    init {
        preferences = context.getSharedPreferences("app", Context.MODE_PRIVATE)
    }

    fun getCalories(): Float {
        return preferences.getFloat(CALORIES,0f)
    }

    fun setCalories(calories: Float?) {
        if(calories != null){
        preferences.edit()
            .putFloat(CALORIES,calories)
            .commit()
        }
        else{
            preferences.edit().remove(CALORIES)
        }
    }

}