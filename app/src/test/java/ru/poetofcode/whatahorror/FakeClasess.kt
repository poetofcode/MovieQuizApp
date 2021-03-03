package ru.poetofcode.whatahorror

import ru.poetofcode.whatahorror.data.Movie
import ru.poetofcode.whatahorror.helper.RandomHelper
import ru.poetofcode.whatahorror.usecase.MovieProvider

const val STEP_INDEX = 4

class FakeRandomHelper : RandomHelper() {

    var startIndex = 0

    fun nextStep() {
        startIndex += STEP_INDEX
    }

    override fun fromRange(range: IntRange, exceptList: List<Int>?): Int {
        return if (exceptList != null)
            startIndex + exceptList.size
        else
            range.first
    }

}

class FakeMovieProvider : MovieProvider {

    private val movies = (1..STEP_INDEX * 2).map {
        Movie("film-$it", listOf("http://test-server.com/image-$it.png"))
    }

    override fun count(): Int {
        return movies.size
    }

    override fun movie(idx: Int): Movie {
        return movies[idx]
    }

}