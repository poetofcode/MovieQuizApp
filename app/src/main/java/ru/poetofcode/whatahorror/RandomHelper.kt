package ru.poetofcode.whatahorror

interface RandomHelper {

    fun fromRange(range: IntRange, exceptList: List<Int> = listOf()): Int

}
