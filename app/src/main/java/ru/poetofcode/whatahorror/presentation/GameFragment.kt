package ru.poetofcode.whatahorror.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ru.poetofcode.whatahorror.R
import ru.poetofcode.whatahorror.databinding.FragmentGameBinding
import ru.poetofcode.whatahorror.usecase.GameView
import ru.poetofcode.whatahorror.usecase.Question

// How to use data-binding with Fragment: https://stackoverflow.com/a/34719627

class GameFragment : BaseFragment(), GameView {

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
        super.onViewCreated(view, savedInstanceState)

        binding.variantHandler = object: VariantTouchHandler {
            override fun createTouchListener(variant: String): View.OnTouchListener {
                return View.OnTouchListener { v, event ->
                    if (event.action != MotionEvent.ACTION_UP
                        || gameViewData.isNextVisible) {
                        return@OnTouchListener false
                    }
                    gameLogic().reply(variant)
                    v.performClick()
                    true
                }
            }
        }

        gameLogic().gameView = this
        gameLogic().ask()
    }

    override fun showQuestion(question: Question) {
        gameViewData = GameViewData.fromQuestion(question)
        binding.question = gameViewData
        binding.question!!.currIndex = gameLogic().answeredQuestionCount() + 1
        binding.question!!.totalCount = gameLogic().totalQuestionCount()
    }

    override fun markVariantAsRight(variantIndex: String) {
        gameViewData.variants.forEach {
            if (variantIndex == it.name) {
                it.color = R.color.colorVariantRight
                it.textColor = R.color.colorLight
                return@forEach
            }
        }
    }

    override fun markVariantAsWrong(variantIndex: String) {
        gameViewData.variants.forEach {
            if (variantIndex == it.name) {
                it.color = R.color.colorVariantWrong
                it.textColor = R.color.colorLight
                return@forEach
            }
        }

    }

    override fun showResult(answeredCount: Int, totalCount: Int) {
        mainActivity().title = "Завершено $answeredCount из $totalCount"
        gameViewData.isNextVisible = true

        if (answeredCount < totalCount) {
            mainActivity().createGameFragment()
        } else {
            mainActivity().createScoreFragment()
        }
    }

}