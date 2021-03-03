package ru.poetofcode.whatahorror.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.poetofcode.whatahorror.DaggerAppComponent
import ru.poetofcode.whatahorror.DataModule
import ru.poetofcode.whatahorror.R
import ru.poetofcode.whatahorror.data.LocalMovieProvider
import ru.poetofcode.whatahorror.helper.RandomHelper
import ru.poetofcode.whatahorror.usecase.GameLogic
import ru.poetofcode.whatahorror.usecase.IView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IView {

    private var logic: GameLogic? = null

    @set:Inject
    var movieProvider: LocalMovieProvider? = null

    @set:Inject
    var randHelper: RandomHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent
            .builder()
            .dataModule(DataModule(this))
            .build()
            .injectMainActivity(this)

        logic = GameLogic(this, movieProvider!!, randHelper!!)
        logic?.ask()
    }

    override fun showQuestion(description: String, imageUrl: String, variants: List<String>) {
        Log.d("tag", "Description: $description,\nimageUrl: $imageUrl,\nvariants: $variants")
    }

    override fun markVariantAsRight(variantIndex: String) {

    }

    override fun markVariantAsWrong(variantIndex: String) {

    }

    override fun showResult(resultText: String) {

    }


}