package com.dclet.recipes.dish

import com.dclet.recipes.di.ActivityScoped
import com.dclet.recipes.di.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.dclet.recipes.dish.DishActivity.Companion.EXTRA_DISH_ID
import dagger.Provides

@Module(includes = [DishModule.ActivityBindings::class])
class DishModule {

    @Provides
    @ActivityScoped
    fun provideTaskId(activity: DishActivity): Long {
        return activity.intent.getLongExtra(EXTRA_DISH_ID,0L)
    }

    @Module
    interface ActivityBindings{
        @FragmentScoped
        @ContributesAndroidInjector
        fun dishFragment(): DishFragment

        @ActivityScoped
        @Binds
        fun dishPresenter(presenter: DishPresenter): DishContract.Presenter

    }

}