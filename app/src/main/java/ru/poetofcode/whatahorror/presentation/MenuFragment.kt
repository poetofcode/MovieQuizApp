package ru.poetofcode.whatahorror.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.poetofcode.whatahorror.R
import ru.poetofcode.whatahorror.databinding.FragmentGameBinding
import ru.poetofcode.whatahorror.databinding.FragmentMenuBinding

class MenuFragment : BaseFragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_menu, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startHandler = View.OnClickListener {
            mainActivity().createGameFragment()
            finish()
        }
    }
}