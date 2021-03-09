package ru.poetofcode.whatahorror.presentation

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected fun gameLogic() = mainActivity().gameLogic!!

    protected fun mainActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

    protected fun finish() {
        mainActivity().closeFragment(this)
    }
}