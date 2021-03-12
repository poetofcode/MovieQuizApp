package ru.poetofcode.whatahorror.presentation

import androidx.annotation.ColorRes
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import ru.poetofcode.whatahorror.R
import ru.poetofcode.whatahorror.usecase.Question

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
    description: String,
    val imageUrl: String = "",
    val variants: List<VariantInfo> = listOf(),
    isNextVisible: Boolean = false
): BaseObservable()
{
    @Bindable var description = description
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable var isNextVisible = isNextVisible
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable var currIndex = 0
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable var totalCount = 0
        set(value) {
            field = value
            notifyChange()
        }

    companion object {

        fun fromQuestion(q: Question): GameViewData {
            return GameViewData(
                q.description,
                q.imageUrls[0],
                q.variants.map { VariantInfo(
                    it, 
                    R.color.colorVariantDefault,
                    R.color.mutedTextDark)
                }
            )
        }

    }
}