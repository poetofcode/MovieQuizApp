package ru.poetofcode.whatahorror.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun getItemId(position: Int): Long {
        return fragments[position].hashCode().toLong() // make sure notifyDataSetChanged() works
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragment(fragment: Fragment) {
        fragments += fragment
        notifyItemInserted(lastIndex())
    }

    fun lastIndex() = fragments.size - 1

    fun clearFragments() {
        fragments.clear()
        notifyDataSetChanged()
    }

    fun remove(fragment: Fragment) {
        fragments
            .find { it.hashCode() == fragment.hashCode() }
            .let {
                fragments.remove(it)
                notifyDataSetChanged()
            }
    }
}