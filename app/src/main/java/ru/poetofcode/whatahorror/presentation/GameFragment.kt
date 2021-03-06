package ru.poetofcode.whatahorror.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_game.*
import ru.poetofcode.whatahorror.R
import ru.poetofcode.whatahorror.databinding.FragmentGameBinding
import ru.poetofcode.whatahorror.usecase.GameView
import java.util.*

// How to use data-binding with Fragment: https://stackoverflow.com/a/34719627

class GameFragment : Fragment(), GameView {

    private lateinit var binding: FragmentGameBinding

    private lateinit var gameViewData: GameViewData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.variantHandler = object: VariantButtonHandler {
            override fun onClick(variant: String) {
                if (gameViewData.isNextVisible) return
                gameLogic().reply(variant)
            }
        }

        gameLogic().gameView = this
        gameLogic().ask()
    }

    private fun gameLogic() = mainActivity().gameLogic!!

    private fun mainActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

    override fun showQuestion(
        description: String,
        imageUrl: String,
        variants: List<String>,
        counterPair: AbstractMap.SimpleEntry<Int, Int>
    ) {
        mainActivity().title = "Завершено ${counterPair.key} из ${counterPair.value}"

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
        gameViewData.variants.forEach {
            if (variantIndex == it.name) {
                it.color = R.color.colorRight
                it.textColor = R.color.colorLight
                return@forEach
            }
        }
    }

    override fun markVariantAsWrong(variantIndex: String) {
        gameViewData.variants.forEach {
            if (variantIndex == it.name) {
                it.color = R.color.colorWrong
                it.textColor = R.color.colorLight
                return@forEach
            }
        }

    }

    override fun showResult(resultText: String) {
        mainActivity().createNewFragment()
        gameViewData.isNextVisible = true
    }

}