package com.dclet.recipes.dish

import com.dclet.recipes.data.source.RecipesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DishPresenter @Inject constructor(var recipeId : Long, var repo : RecipesRepository): DishContract.Presenter {

    var view : DishContract.View? = null

    override fun loadDetails() {
        var subscribe = repo.getRecipe(recipeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ dish ->
                if(dish != null)
                view?.setDetails(dish)
            },{err->

            })
    }

    override fun attach(view: DishContract.View) {
        this.view = view
       loadDetails()
    }

    override fun dettach() {
    }
}