package com.dclet.recipes.di

import com.dclet.recipes.dish.DishActivity
import com.dclet.recipes.dish.DishModule
import com.dclet.recipes.dishes.DishesActivity
import com.dclet.recipes.dishes.DishesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [DishesModule::class])
    abstract fun dishesActivity(): DishesActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [DishModule::class])
    abstract fun dishActivity(): DishActivity
}