package ru.poetofcode.whatahorror.data

import com.google.gson.Gson
import ru.poetofcode.whatahorror.usecase.MovieProvider
import java.io.InputStream

// Android read text raw resource file: https://stackoverflow.com/a/58497079

class LocalMovieProvider(fileStream: InputStream, gson: Gson) : MovieProvider {

    private var movies: Array<Movie>

    init {
        val fileContents = fileStream.bufferedReader().use { it.readText() }
        movies = gson.fromJson(fileContents, Array<Movie>::class.java)
    }

    override fun count(): Int {
        return movies.size
    }

    override fun movie(idx: Int): Movie {
        return movies[idx]
    }

}