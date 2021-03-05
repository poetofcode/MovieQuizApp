package ru.poetofcode.whatahorror.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_game.*
import ru.poetofcode.whatahorror.R
import ru.poetofcode.whatahorror.databinding.FragmentGameBinding
import ru.poetofcode.whatahorror.usecase.GameView

// How to use data-binding with Fragment: https://stackoverflow.com/a/34719627

class GameFragment : Fragment(), GameView {

    private lateinit var binding: FragmentGameBinding

    private lateinit var questionInfo: QuestionInfo

    private var completed = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        questionInfo = QuestionInfo(
            "Эти кнопки пока не работают",
            listOf("one", "two", "three", "four")
        )

        binding.question = questionInfo
        binding.variantHandler = object: VariantButtonHandler {
            override fun onClick(variant: String) {
                if (completed) return
                mainActivity().onVariantSelected(variant)
            }
        }

        // TODO replace on binding
        nextPage.setOnClickListener { mainActivity().onNextPageClicked() }
    }

    private fun mainActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

    override fun showQuestion(description: String, imageUrl: String, variants: List<String>) {
        if (completed) return
        mainActivity().onNextQuestion(description, imageUrl, variants)
    }

    override fun markVariantAsRight(variantIndex: String) {
        if (completed) return
        binding.question = QuestionInfo(
            questionInfo.description,
            questionInfo.variants.map { if (variantIndex == it) return@map "(ДА) $it" else it }
        )
    }

    override fun markVariantAsWrong(variantIndex: String) {
        if (completed) return
        binding.question = QuestionInfo(
            questionInfo.description,
            questionInfo.variants.map { if (variantIndex == it) return@map "(НЕТ) $it" else it }
        )
    }

    override fun showResult(resultText: String) {
        completed = true
    }

}