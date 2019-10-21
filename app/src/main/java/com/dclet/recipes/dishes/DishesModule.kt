package com.dclet.recipes.dishes

import dagger.Module
import dagger.Binds
import com.dclet.recipes.di.ActivityScoped
import dagger.android.ContributesAndroidInjector
import com.dclet.recipes.di.FragmentScoped


@Module abstract class DishesModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun dishesFragment(): DishesFragment

    @ActivityScoped
    @Binds
    abstract fun dishesPresenter(presenter: DishesPresenter): DishesContract.Presenter

}