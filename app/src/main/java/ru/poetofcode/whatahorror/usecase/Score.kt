package ru.poetofcode.whatahorror.usecase

import kotlin.math.roundToInt

enum class WinnerRank {
    WINNER_BEST,
    WINNER_GOLD,
    WINNER_SILVER,
    WINNER_BRONZE;

    companion object {
        fun createRank(rightCount: Int, totalCount: Int): WinnerRank {
            val percent = (rightCount / totalCount * 100).toDouble().roundToInt()
            return when {
                percent < 100 -> WINNER_GOLD
                percent < 90 -> WINNER_SILVER
                percent < 60 -> WINNER_BRONZE
                else -> WINNER_BEST
            }
        }
    }
}

data class Score(
    var answerRightCount: Int,
    var answerTotalCount: Int,
    var winnerRank: WinnerRank
)