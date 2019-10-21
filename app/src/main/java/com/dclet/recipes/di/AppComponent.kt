package com.dclet.recipes.di

import android.app.Application
import com.dclet.recipes.RecipesApplication
import com.dclet.recipes.data.NetworkModule
import com.dclet.recipes.data.source.RecipesRepository
import com.dclet.recipes.data.source.RecipesRepositoryModule
import dagger.android.AndroidInjector
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ActivityBindingModule::class,
    NetworkModule::class,
    RecipesRepositoryModule::class])
interface AppComponent : AndroidInjector<RecipesApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}