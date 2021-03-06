package ru.poetofcode.whatahorror.usecase

import ru.poetofcode.whatahorror.data.Movie
import ru.poetofcode.whatahorror.helper.RandomHelper
import java.util.*

class GameLogic(
    private val movieProvider: MovieProvider,
    private val randHelper: RandomHelper
) {

    var gameView: GameView? = null

    private val lastQuestions = mutableListOf<Question>()

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

        for (i in 1..4) {
            indexes += randHelper.fromRange(0 until count, indexes)
            movies += movieProvider.movie(indexes.last())
        }

        val rightIndex = randHelper.fromRange(0 until movies.size)

        return Question(
            description = "Из какого фильма этот монстр?",
            imageUrls = movies[rightIndex].imageUrls,
            variants = movies.map { it.name },
            rightVariant = movies[rightIndex].name
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