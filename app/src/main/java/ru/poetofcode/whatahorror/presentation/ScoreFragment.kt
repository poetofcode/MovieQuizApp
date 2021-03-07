package ru.poetofcode.whatahorror.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.poetofcode.whatahorror.R
import ru.poetofcode.whatahorror.databinding.FragmentScoreBinding
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
                    WinnerRank.WINNER_BEST -> "ФАНАТ УЖАСТИКОВ"
                    WinnerRank.WINNER_GOLD -> "ОПАСНЫЙ ЗНАТОК"
                    WinnerRank.WINNER_SILVER -> "ЛЮБИТЕЛЬ КОШМАРОВ"
                    else -> "ПУГЛИВЫЙ НОВИЧОК"
                },
                "file:///android_asset/award.png",
                "GITHUB_URL"
            )
        }
    }
}

class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_score, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.score = ScoreViewData.fromScore(gameLogic().score()!!)
        binding.restartHandler = object: View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("tag", "restartHandler(): on click")


                mainActivity().restartGame()
            }
        }
    }

    private fun gameLogic() = mainActivity().gameLogic!!

    private fun mainActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

}