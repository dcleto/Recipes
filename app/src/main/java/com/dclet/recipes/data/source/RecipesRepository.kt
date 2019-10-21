package com.dclet.recipes.data.source
import com.dclet.recipes.data.RecipesDataSource
import com.dclet.recipes.data.source.local.RecipesLocalDataSource
import com.dclet.recipes.data.source.remote.RecipesRemoteDataSource
import com.dclet.recipes.model.DishDetail
import com.dclet.recipes.model.MealPlan
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers


class RecipesRepository (var localDb : RecipesDataSource, var remoteDb : RecipesDataSource): RecipesDataSource{

    override fun getRecipes(
        category: String?,
        calories: Float?,
        period: String?
    ): Maybe<MealPlan> {

        var getLocal = localDb.getRecipes(category,calories,period)

        var getRemote = getRemoteAndSaveMealPlan(category,calories,period)

        return getLocal.switchIfEmpty(getRemote)
    }

    private fun getRemoteAndSaveMealPlan(category: String?,
                                         calories: Float?,
                                         period: String?) : Maybe<MealPlan>{
        return remoteDb
            .getRecipes(category,calories,period)
            .subscribeOn(Schedulers.io())
            .flatMap{ localDb.saveMealPlan(it,category,calories).andThen(Maybe.just(it))}
     }

    private fun getRemoteAndSaveDish(id : Long) : Maybe<DishDetail>{
        return remoteDb
            .getRecipe(id)
            .subscribeOn(Schedulers.io())
            .doAfterSuccess {  localDb.saveDish(it).subscribe() }

    }

    override fun getRecipe(id: Long): Maybe<DishDetail> {
        return  localDb.getRecipe(id).switchIfEmpty(getRemoteAndSaveDish(id))
    }

    override fun saveMealPlan(mealPlan: MealPlan, category: String?, calories: Float?): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveDish(dishDetail: DishDetail): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}