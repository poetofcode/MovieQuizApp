package ru.poetofcode.whatahorror.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragment(fragment: Fragment) {
        fragments += fragment
        notifyItemInserted(fragments.size - 1)
    }

}