package ru.poetofcode.whatahorror.helper

import kotlin.random.Random

open class RandomHelper {

    open fun fromRange(range: IntRange, exceptList: List<Int>? = null): Int {
        val filteredList = range.filter { value ->
            exceptList?.find { value == it } == null
        }

        val rndIdx = Random.nextInt(filteredList.size)
        return filteredList[rndIdx]
    }

}
