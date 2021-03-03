package ru.poetofcode.whatahorror.helper

interface RandomHelper {

    fun fromRange(range: IntRange, exceptList: List<Int>? = null): Int

}
