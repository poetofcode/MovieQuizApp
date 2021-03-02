package ru.poetofcode.whatahorror

import ru.poetofcode.whatahorror.data.Movie
import ru.poetofcode.whatahorror.helper.RandomHelper
import ru.poetofcode.whatahorror.usecase.IView
import ru.poetofcode.whatahorror.usecase.MovieProvider

class FakeRandomHelper : RandomHelper {

    override fun fromRange(range: IntRange, exceptList: List<Int>): Int {
        return 0
    }

}

class FakeMovieProvider : MovieProvider {

    override fun count(): Int {
        return 0
    }

    override fun movie(idx: Int): Movie {
        return Movie("test", mutableListOf())
    }

}

// TODO replace on Mock view
class FakeView: IView {

    override fun showQuestion(description: String, imageUrl: String, variants: List<String>) {
        // Do nothing
    }

    override fun markVariantAsRight(variantIndex: Int) {
        // Do nothing
    }

    override fun markVariantAsWrong(variantIndex: Int) {
        // Do nothing
    }

    override fun showResult(resultText: String) {
        // Do nothing
    }

}