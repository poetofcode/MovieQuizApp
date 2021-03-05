package ru.poetofcode.whatahorror.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import ru.poetofcode.whatahorror.DaggerAppComponent
import ru.poetofcode.whatahorror.DataModule
import ru.poetofcode.whatahorror.R
import ru.poetofcode.whatahorror.data.LocalMovieProvider
import ru.poetofcode.whatahorror.helper.RandomHelper
import ru.poetofcode.whatahorror.usecase.GameLogic
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var logic: GameLogic? = null

    @set:Inject
    var movieProvider: LocalMovieProvider? = null

    @set:Inject
    var randHelper: RandomHelper? = null

    var pagerAdapter: FragmentPagerAdapter? = null

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
        // onNextPageClicked()

        logic = GameLogic(movieProvider!!, randHelper!!)
        logic?.ask()
    }

    fun onNextQuestion(description: String, imageUrl: String, variants: List<String>) {
        Log.d("tag", "Description: $description,\nimageUrl: $imageUrl,\nvariants: $variants")

        val lastIndex = pagerAdapter?.addFragment(GameFragment().apply {
            arguments = Bundle().apply {
                putString("description", description)
                putString("answer", variants[0])
                putStringArrayList("variants", ArrayList(variants))
            }
        })
        viewPager.setCurrentItem(lastIndex ?: return, true)
    }

    fun onNextPageClicked() {
        logic!!.ask()
    }

    fun onVariantSelected(variant: String) {
        logic!!.reply(variant)
    }

}