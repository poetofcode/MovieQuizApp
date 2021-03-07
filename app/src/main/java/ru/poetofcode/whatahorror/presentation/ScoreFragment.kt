package ru.poetofcode.whatahorror.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.poetofcode.whatahorror.R
import ru.poetofcode.whatahorror.usecase.Score
import ru.poetofcode.whatahorror.usecase.WinnerRank

class ScoreViewData(
    var answerRightCount: Int,
    var answerTotalCount: Int,
    var winnerTitle: String,
    var rewardImageUrl: String,
    var projectUrl: String
) {

    companion object {
        fun fromScore(s: Score): ScoreViewData {
            return ScoreViewData(
                s.answerRightCount,
                s.answerTotalCount,
                when (s.winnerRank) {
                    WinnerRank.WINNER_BEST -> "ЧЕМПИОН"
                    WinnerRank.WINNER_GOLD -> "ЗНАТОК"
                    WinnerRank.WINNER_SILVER -> "ЛЮБИТЕЛЬ"
                    else -> "НОВИЧОК"
                },
                "REWARD_URL",
                "GITHUB_URL"
            )
        }
    }
}

class ScoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_score, container, false)
    }
}