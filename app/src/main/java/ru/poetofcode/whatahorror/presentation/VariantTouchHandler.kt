package ru.poetofcode.whatahorror.presentation

import android.view.View

interface VariantTouchHandler {

    fun createTouchListener(variant: String): View.OnTouchListener

}