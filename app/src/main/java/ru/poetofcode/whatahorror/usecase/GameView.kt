package ru.poetofcode.whatahorror.usecase

interface GameView {

    fun showQuestion(description: String, imageUrl: String, variants: List<String>)

    fun markVariantAsRight(variantIndex: String)

    fun markVariantAsWrong(variantIndex: String)

    fun showResult(resultText: String)

}
