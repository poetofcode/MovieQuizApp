package ru.poetofcode.whatahorror.usecase

import android.util.Log
import ru.poetofcode.whatahorror.data.Movie
import ru.poetofcode.whatahorror.helper.RandomHelper
import java.util.*

class GameLogic(
    private val movieProvider: MovieProvider,
    private val randHelper: RandomHelper
) {

    var gameView: GameView? = null

    private val lastQuestions = mutableListOf<Question>()

    private val usedMovieindexes = mutableListOf<Int>()

    init {
        resetGame()
    }

    fun ask() {
        val q = lastQuestion()
        // println("Last question: $q")

        gameView?.showQuestion(
            q.description,
            q.imageUrls[0],
            q.variants,
            AbstractMap.SimpleEntry(lastQuestions.count { it.result.isAnswered() }, movieProvider.count())
        )
    }

    private fun lastQuestion(): Question {
        if (lastQuestions.size > 0 && !lastQuestions.last().result.isAnswered()) {
            return lastQuestions.last()
        }
        lastQuestions += createQuestion()

        return lastQuestions.last()
    }

    private fun createQuestion(): Question {
        val count = movieProvider.count()
        val indexes = mutableListOf<Int>()
        val movies = mutableListOf<Movie>()

        // Select unused movie as right variant
        indexes += randHelper.fromRange(0 until count, usedMovieindexes)
        usedMovieindexes += indexes.last()
        movies += movieProvider.movie(indexes.last())
        val movie = movies.last()

        // Select other wrong variants
        for (i in 1..3) {
            indexes += randHelper.fromRange(0 until count, indexes)
            movies += movieProvider.movie(indexes.last())
        }

        movies.shuffle()

        return Question(
            description = "Из какого фильма этот монстр?",
            imageUrls = movie.imageUrls,
            variants = movies.map { it.name },
            rightVariant = movie.name
        )
    }

    fun reply(selectedVariant: String) {
        val q = lastQuestion()

        if (selectedVariant == q.rightVariant) {
            gameView?.markVariantAsRight(selectedVariant)
            gameView?.showResult("Вы угадали!")
            q.result = AnswerResult.RESULT_RIGHT
        } else {
            gameView?.markVariantAsWrong(selectedVariant)
            gameView?.markVariantAsRight(q.rightVariant)
            gameView?.showResult("Ответ неверный :(")
            q.result = AnswerResult.RESULT_WRONG
        }
    }

    fun resetGame() {
        lastQuestions.clear()
    }

}