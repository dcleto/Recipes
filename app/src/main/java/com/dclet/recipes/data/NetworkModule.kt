package com.dclet.recipes.data

import com.dclet.recipes.data.source.remote.RecipesRemoteDataSource
import com.dclet.recipes.data.source.remote.RecipesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule  {

    @Provides
    fun providesRetrofit() : Retrofit{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return retrofit.build()
    }

    @Provides
    fun providesRecipesService(retrofit: Retrofit) : RecipesService{
        return retrofit.create(RecipesService::class.java)
    }

}