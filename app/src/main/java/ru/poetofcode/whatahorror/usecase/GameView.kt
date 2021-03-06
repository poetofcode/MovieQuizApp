package ru.poetofcode.whatahorror.usecase

import java.util.AbstractMap

interface GameView {

    fun showQuestion(
        description: String,
        imageUrl: String,
        variants: List<String>,
        counterPair: AbstractMap.SimpleEntry<Int, Int>
    )

    fun markVariantAsRight(variantIndex: String)

    fun markVariantAsWrong(variantIndex: String)

    fun showResult(resultText: String)

}
