package ru.poetofcode.whatahorror.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.poetofcode.whatahorror.R

class MainActivity : AppCompatActivity() {

    var pagerAdapter: FragmentPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init view pager
        viewPager.adapter = FragmentPagerAdapter(this).apply {
            pagerAdapter = this
        }

        createNewFragment()
    }

    fun openLastFragment() {
        viewPager.setCurrentItem(pagerAdapter!!.lastIndex(), true)
    }

    fun createNewFragment() {
        pagerAdapter!!.addFragment(GameFragment())
        viewPager.offscreenPageLimit = pagerAdapter!!.lastIndex() + 1
    }

}