package ru.poetofcode.whatahorror

interface MovieProvider {

    fun count(): Int

    fun movie(idx: Int): Movie

}
