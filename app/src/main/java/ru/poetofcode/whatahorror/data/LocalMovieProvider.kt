package ru.poetofcode.whatahorror.data

import ru.poetofcode.whatahorror.usecase.MovieProvider

class LocalMovieProvider(val filePath: String) : MovieProvider {

    override fun count(): Int {
        return 0
    }

    override fun movie(idx: Int): Movie {
        return Movie("", listOf())
    }

}