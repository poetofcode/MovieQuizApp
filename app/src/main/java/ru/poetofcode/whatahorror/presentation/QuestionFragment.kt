package ru.poetofcode.whatahorror.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_question.*
import ru.poetofcode.whatahorror.R

class QuestionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        descriptionText.text = requireArguments().getString("description")

        nextPage.setOnClickListener { mainActivity().onNextPageClicked() }
    }

    private fun mainActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

}