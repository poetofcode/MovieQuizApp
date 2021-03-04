package ru.poetofcode.whatahorror.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_question.*
import ru.poetofcode.whatahorror.R
import ru.poetofcode.whatahorror.databinding.FragmentQuestionBinding

// How to use data-binding with Fragment: https://stackoverflow.com/a/34719627

class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_question, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.question = QuestionInfo(
            "Эти кнопки пока не работают",
            listOf("one", "two", "three", "four")
        )
        binding.variantHandler = object: VariantButtonHandler {
            override fun onClick(variant: String) {
                // println("Variant clicked: $variant")
                mainActivity().onVariantSelected(variant)
            }
        }

        nextPage.setOnClickListener { mainActivity().onNextPageClicked() }
    }

    private fun mainActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

}