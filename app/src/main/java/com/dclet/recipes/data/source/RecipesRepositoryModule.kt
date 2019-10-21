package com.dclet.recipes.data.source

import android.content.Context
import com.dclet.recipes.data.RecipesDataSource
import com.dclet.recipes.data.source.local.DishDao
import com.dclet.recipes.data.source.local.DishDatabase
import com.dclet.recipes.data.source.local.RecipesLocalDataSource
import com.dclet.recipes.data.source.remote.RecipesRemoteDataSource
import com.dclet.recipes.data.source.remote.RecipesService
import com.dclet.recipes.utils.Preferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RecipesRepositoryModule {

    @Singleton
    @Remote
    @Provides
    fun providesRemoteSource(service : RecipesService) : RecipesDataSource{
        return RecipesRemoteDataSource(service)
    }

    @Singleton
    @Local
    @Provides
    fun providesLocalSource(db : DishDatabase) : RecipesDataSource{
        return RecipesLocalDataSource(db.dishDao)
    }

    @Singleton
    @Provides
    fun getDishRepository(@Local localDataSource: RecipesDataSource, @Remote recipesRemoteDataSource: RecipesDataSource ) : RecipesRepository {
        return RecipesRepository(localDataSource,recipesRemoteDataSource)
    }


    @Singleton
    @Provides
    fun providesDishDatabase(context : Context) : DishDatabase {
        return DishDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun providesPreferences(context: Context): Preferences{
        return Preferences(context)
    }

}