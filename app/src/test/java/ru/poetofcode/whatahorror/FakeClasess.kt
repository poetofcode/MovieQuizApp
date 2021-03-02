package ru.poetofcode.whatahorror

import ru.poetofcode.whatahorror.data.Movie
import ru.poetofcode.whatahorror.helper.RandomHelper
import ru.poetofcode.whatahorror.usecase.IView
import ru.poetofcode.whatahorror.usecase.MovieProvider

class FakeRandomHelper : RandomHelper {

    override fun fromRange(range: IntRange, exceptList: List<Int>): Int {
        return range.first + exceptList.size
    }

}

class FakeMovieProvider : MovieProvider {

    private var invokeTimes: Int = 0

    private val movies = listOf("yes", "no", "no-too", "no-again")
        .map {
            Movie(it, listOf("http://test-server.com/image.png"))
        }

    private val movies2 = listOf("film1", "film2", "film3", "film4")
        .map {
            Movie(it, listOf("http://test-server.com/image2.png"))
        }

    override fun count(): Int {
        invokeTimes++
        return movies.size
    }

    override fun movie(idx: Int): Movie {
        return if (invokeTimes % 2 != 0) movies[idx] else movies2[idx]
    }

}