package ru.poetofcode.whatahorror.usecase

interface GameView {

    fun showQuestion(question: Question)

    fun markVariantAsRight(variantIndex: String)

    fun markVariantAsWrong(variantIndex: String)

    fun showResult(answeredCount: Int, totalCount: Int)

}
