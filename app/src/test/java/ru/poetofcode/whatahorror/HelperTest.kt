package ru.poetofcode.whatahorror

import org.junit.Assert
import org.junit.Test
import ru.poetofcode.whatahorror.helper.RandomHelper

class HelperTest {

    @Test
    fun `random helper fromRange() test`() {
        val randomHelper = RandomHelper()

        Assert.assertTrue(isValueInRange(randomHelper.fromRange(5..10), 5..10))
        Assert.assertEquals(4, randomHelper.fromRange(1..5, listOf(1, 2, 3)))
    }

    private fun isValueInRange(value: Int, range: IntRange): Boolean {
        return range.find { it == value } != null
    }

}