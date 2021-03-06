package ru.poetofcode.whatahorror.presentation

import androidx.annotation.ColorRes
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class VariantInfo(
    val name: String,
    @ColorRes val color: Int? = null
)

class QuestionInfo(
    var description: String,
    var imageUrl: String = "",
    variants: List<VariantInfo> = listOf()
) : BaseObservable() {
    @Bindable var variants: List<VariantInfo> = variants
        set(value) {
            field = value
            notifyChange()
        }
}