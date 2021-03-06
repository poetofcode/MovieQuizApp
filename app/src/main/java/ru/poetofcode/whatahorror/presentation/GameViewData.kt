package ru.poetofcode.whatahorror.presentation

import androidx.annotation.ColorRes
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class VariantInfo(
    name: String,
    color: Int? = null,
    textColor: Int? = null
): BaseObservable()
{
    @Bindable var name = name
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable @ColorRes var color = color
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable @ColorRes var textColor = textColor
        set(value) {
            field = value
            notifyChange()
        }
}

class GameViewData(
    val description: String,
    val imageUrl: String = "",
    val variants: List<VariantInfo> = listOf(),
    isNextVisible: Boolean = false
): BaseObservable()
{
    @Bindable var isNextVisible = isNextVisible
        set(value) {
            field = value
            notifyChange()
        }
}