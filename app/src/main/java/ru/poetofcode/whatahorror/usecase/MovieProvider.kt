package ru.poetofcode.whatahorror.usecase

import ru.poetofcode.whatahorror.data.Movie

interface MovieProvider {

    fun count(): Int

    fun movie(idx: Int): Movie

}
