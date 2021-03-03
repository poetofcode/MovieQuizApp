package ru.poetofcode.whatahorror

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.core.AnyOf.anyOf
import org.junit.Assert
import org.junit.Test
import ru.poetofcode.whatahorror.helper.RandomHelper

class HelperTest {

    @Test
    fun `Random helper fromRange() test`() {
        val randomHelper = RandomHelper()

        Assert.assertThat(
            randomHelper.fromRange(5..8),
            anyOf(equalTo(5), equalTo(6), equalTo(7), equalTo(8))
        )
        Assert.assertThat(
            randomHelper.fromRange(1..4, listOf(1, 2, 4)),
            equalTo(3)
        )
    }

    /*
    private fun isValueInRange(value: Int, range: IntRange): Boolean {
        return range.find { it == value } != null
    }
    */

}