package ru.poetofcode.whatahorror.usecase

import android.util.Log
import kotlin.math.roundToInt

enum class WinnerRank {
    WINNER_BEST,
    WINNER_GOLD,
    WINNER_SILVER,
    WINNER_BRONZE;

    companion object {
        fun createRank(rightCount: Int, totalCount: Int): WinnerRank {
            val percent = (rightCount.toDouble() / totalCount.toDouble() * 100).roundToInt()
            Log.d("tag", "Percent: $percent")
            return when {
                percent > 99 -> WINNER_BEST
                percent in 70..99 -> WINNER_GOLD
                percent in 40..69 -> WINNER_SILVER
                else -> WINNER_BRONZE
            }
        }
    }
}

data class Score(
    var answerRightCount: Int,
    var answerTotalCount: Int,
    var winnerRank: WinnerRank
)