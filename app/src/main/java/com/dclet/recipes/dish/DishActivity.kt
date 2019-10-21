package com.dclet.recipes.dish

import android.os.Bundle
import com.dclet.recipes.R
import com.dclet.recipes.data.source.local.DishDatabase
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_dish.*
import javax.inject.Inject

class DishActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var dishFragmentProvider: Lazy<DishFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dish)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var fragment = (supportFragmentManager.findFragmentById(R.id.frame) as DishFragment?)?: dishFragmentProvider.get()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

    companion object{
        val EXTRA_DISH_ID = "task_id"
    }
}
