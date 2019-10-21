package com.dclet.recipes.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    fun bindContext(application: Application): Context{
        return application.applicationContext
    }
}