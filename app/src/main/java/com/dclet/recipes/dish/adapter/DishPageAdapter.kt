package com.dclet.recipes.dish.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class DishPageAdapter (fm: FragmentManager,var fragments : List<Fragment>, var titles : List<String>) : FragmentStatePagerAdapter(fm){

    override fun getItem(i: Int): Fragment {
        return fragments[i]
    }

    override fun getCount(): Int  = fragments.size

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

}