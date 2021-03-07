package ru.poetofcode.whatahorror.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.poetofcode.whatahorror.DaggerAppComponent
import ru.poetofcode.whatahorror.DataModule
import ru.poetofcode.whatahorror.R
import ru.poetofcode.whatahorror.usecase.GameLogic
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    var pagerAdapter: FragmentPagerAdapter? = null

    @set:Inject
    var gameLogic: GameLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent
            .builder()
            .dataModule(DataModule(this))
            .build()
            .injectMainActivity(this)

        // Init view pager
        viewPager.adapter = FragmentPagerAdapter(this).apply {
            pagerAdapter = this
        }

        createGameFragment()
    }

    fun openLastFragment() {
        viewPager.setCurrentItem(pagerAdapter!!.lastIndex(), true)
    }

    fun createGameFragment() {
        pagerAdapter!!.addFragment(GameFragment())
        viewPager.offscreenPageLimit = pagerAdapter!!.lastIndex() + 1
    }

    fun createScoreFragment() {
        // Log.d("tag", "Invoked createScoreFragment()")
        pagerAdapter!!.addFragment(ScoreFragment())
        viewPager.offscreenPageLimit = pagerAdapter!!.lastIndex() + 1
    }

}