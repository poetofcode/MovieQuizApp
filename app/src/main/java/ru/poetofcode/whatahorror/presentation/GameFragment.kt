package ru.poetofcode.whatahorror.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_game.*
import ru.poetofcode.whatahorror.DaggerAppComponent
import ru.poetofcode.whatahorror.DataModule
import ru.poetofcode.whatahorror.R
import ru.poetofcode.whatahorror.databinding.FragmentGameBinding
import ru.poetofcode.whatahorror.usecase.GameLogic
import ru.poetofcode.whatahorror.usecase.GameView
import javax.inject.Inject

// How to use data-binding with Fragment: https://stackoverflow.com/a/34719627

class GameFragment : Fragment(), GameView {

    private lateinit var binding: FragmentGameBinding

    private lateinit var gameViewData: GameViewData

    @set:Inject
    var gameLogic: GameLogic? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DaggerAppComponent
            .builder()
            .dataModule(DataModule(mainActivity()))
            .build()
            .injectGameFragment(this)

        binding.variantHandler = object: VariantButtonHandler {
            override fun onClick(variant: String) {
                if (gameViewData.isNextVisible) return
                gameLogic!!.reply(variant)
            }
        }

        gameLogic!!.gameView = this
        gameLogic!!.ask()
    }

    private fun mainActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

    override fun showQuestion(description: String, imageUrl: String, variants: List<String>) {
        gameViewData = GameViewData(
            description,
            imageUrl,
            variants.map { VariantInfo(it, R.color.colorNotAnswered, R.color.colorDark) }
        )
        binding.question = gameViewData

        // TODO replace on binding
        nextPage.setOnClickListener { mainActivity().openLastFragment() }
    }

    override fun markVariantAsRight(variantIndex: String) {
        Log.d("tag", "markVariantAsRight() => $variantIndex")

        gameViewData.variants.forEach {
            if (variantIndex == it.name) {
                it.color = R.color.colorRight
                it.textColor = R.color.colorLight
                return@forEach
            }
        }
    }

    override fun markVariantAsWrong(variantIndex: String) {
        Log.d("tag", "markVariantAsWrong() => $variantIndex")

        gameViewData.variants.forEach {
            if (variantIndex == it.name) {
                it.color = R.color.colorWrong
                it.textColor = R.color.colorLight
                return@forEach
            }
        }

    }

    override fun showResult(resultText: String) {
        Log.d("tag", "showResult() => $resultText")

        mainActivity().createNewFragment()
        gameViewData.isNextVisible = true
    }

}