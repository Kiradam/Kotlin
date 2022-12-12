@file:Suppress("DEPRECATION")

package hu.bme.aut.android.tetris_elao0e.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import hu.bme.aut.android.tetris_elao0e.fragment.MenuFragment
import hu.bme.aut.android.tetris_elao0e.fragment.RecordFragment

class MenuPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    companion object {
        private const val NUM_PAGES = 2
    }

    override fun getCount(): Int = NUM_PAGES

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MenuFragment()
            1 -> RecordFragment()
            else -> throw IllegalArgumentException("No such page!")
        }
    }
}