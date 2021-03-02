package ru.poetofcode.whatahorror.usecase

interface IView {

    fun showQuestion(description: String, imageUrl: String, variants: List<String>)

    fun markVariantAsRight(variantIndex: Int)

    fun markVariantAsWrong(variantIndex: Int)

    fun showResult(resultText: String)

}
