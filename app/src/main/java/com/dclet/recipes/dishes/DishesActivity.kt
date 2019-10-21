package com.dclet.recipes.dishes

import android.os.Bundle
import com.dclet.recipes.R
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DishesActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var dishFragmentProvider: Lazy<DishesFragment>

    @Inject
    lateinit var dishPresenter: DishesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wha_to_cook)
        var fragment = supportFragmentManager.findFragmentById(R.id.frame) as DishesFragment? ?: dishFragmentProvider.get()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame,fragment)
            .commit()

        if (savedInstanceState != null) {
            val currentFiltering = savedInstanceState.getInt("CURRENT_POSITION")
            dishPresenter.setCategoryPosition(currentFiltering)
        }
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("CURRENT_POSITION",dishPresenter.getCategoryPosition())
        super.onSaveInstanceState(outState)
    }
}
