package ru.poetofcode.whatahorror.data

import ru.poetofcode.whatahorror.usecase.MovieProvider
import java.io.InputStream

// Android read text raw resource file: https://stackoverflow.com/a/58497079

class LocalMovieProvider(val fileStream: InputStream) : MovieProvider {

    override fun count(): Int {
        return 0
    }

    override fun movie(idx: Int): Movie {
        return Movie("", listOf())
    }

}