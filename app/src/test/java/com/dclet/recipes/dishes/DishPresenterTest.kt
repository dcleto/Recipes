package com.dclet.recipes.dishes

import com.dclet.recipes.data.RecipesDataSource
import com.dclet.recipes.data.source.RecipesRepository
import com.dclet.recipes.model.MealPlan
import com.dclet.recipes.utils.Preferences
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Maybe
import io.reactivex.Observable
import org.junit.Before
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.Scheduler
import org.junit.After
import org.junit.BeforeClass
import org.mockito.Mockito.*


class DishPresenterTest {
    @Rule
    @JvmField
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var recipesRepo: RecipesRepository

    @Mock
    lateinit var dishesView: DishesContract.View

    @Mock
    lateinit var preferences: Preferences

    @InjectMocks
    lateinit var presenter: DishesPresenter

    companion object {

        @BeforeClass  @JvmStatic fun setup() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { s -> Schedulers.trampoline() }
        }
    }

    @Before
    fun setUpPresenter(){
        presenter.attach(dishesView)
    }

    @Test
    fun clickOnDish_OpensDishDetail(){
        val ID = 1L;
        presenter.openRecipe(ID)

        verify(dishesView).showRecipe(ID)
    }

    @Test
    fun clickCalories_showsPickerDialog(){

        presenter.updateCalories()
         verify(dishesView).showCaloriesPicker(any())
    }

    @Test
    fun onCaloriesChanged_updateMealPlan(){
        val CALORIES = 1500f
        `when`(dishesView.showCaloriesPicker (any())).thenAnswer {
            val argument = it.arguments[0]
            val completion = argument as ((rawResult: Float?) -> Unit)
            completion.invoke(CALORIES)
        }

        `when`(recipesRepo.getRecipes(any(), any(), any())).thenReturn(Maybe.just( mock<MealPlan>()))
        presenter.updateCalories()

        verify(preferences).setCalories(CALORIES)
        verify(dishesView).showCalories(CALORIES)
        verify(recipesRepo).getRecipes(any(), floatThat { f->f==CALORIES }, any())

    }

}