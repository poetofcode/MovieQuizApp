package ru.poetofcode.whatahorror.presentation

import android.os.Bundle
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

    private lateinit var questionInfo: QuestionInfo

    private var completed = false

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

        gameLogic!!.gameView = this
        gameLogic!!.ask()
    }

    private fun mainActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

    override fun showQuestion(description: String, imageUrl: String, variants: List<String>) {
        if (completed) return

        // TODO replace on binding
        nextPage.setOnClickListener { mainActivity().openNewFragment() }

        questionInfo = QuestionInfo(
            description,
            imageUrl,
            variants
        )

        binding.question = questionInfo
        binding.variantHandler = object: VariantButtonHandler {
            override fun onClick(variant: String) {
                if (completed) return
                gameLogic!!.reply(variant)
            }
        }
    }

    override fun markVariantAsRight(variantIndex: String) {
        if (completed) return
        questionInfo = QuestionInfo(
            questionInfo.description,
            questionInfo.imageUrl,
            questionInfo.variants.map { if (variantIndex == it) return@map "(ДА) $it" else it }
        )
        binding.question = questionInfo
    }

    override fun markVariantAsWrong(variantIndex: String) {
        if (completed) return
        questionInfo = QuestionInfo(
            questionInfo.description,
            questionInfo.imageUrl,
            questionInfo.variants.map { if (variantIndex == it) return@map "(НЕТ) $it" else it }
        )
        binding.question = questionInfo
    }

    override fun showResult(resultText: String) {
        completed = true
    }

}