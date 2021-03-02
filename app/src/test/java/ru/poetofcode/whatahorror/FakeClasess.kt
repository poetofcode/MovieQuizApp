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