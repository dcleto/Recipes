package com.dclet.recipes

import com.dclet.recipes.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class RecipesApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}