package ru.poetofcode.whatahorror.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
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

        createMenuFragment()
    }

    fun openLastFragment() {
        viewPager.setCurrentItem(pagerAdapter!!.lastIndex(), true)
    }

    fun closeFragment(fragment: Fragment) {
        pagerAdapter!!.remove(fragment)
    }

    fun restartGame() {
        pagerAdapter!!.clearFragments()
        title = resources.getString(R.string.app_name)
        gameLogic?.resetGame()
        createMenuFragment()
    }

    fun createGameFragment() {
        pagerAdapter!!.addFragment(GameFragment())
        scrollToEnd()
    }

    fun createScoreFragment() {
        pagerAdapter!!.addFragment(ScoreFragment())
        scrollToEnd()
    }

    private fun createMenuFragment() {
        pagerAdapter!!.addFragment(MenuFragment())
        scrollToEnd()
    }

    private fun scrollToEnd() {
        viewPager.offscreenPageLimit = pagerAdapter!!.lastIndex() + 1
    }

}