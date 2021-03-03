package ru.poetofcode.whatahorror.usecase

import ru.poetofcode.whatahorror.data.Movie
import ru.poetofcode.whatahorror.helper.RandomHelper

class GameLogic(
    private val view: IView,
    private val movieProvider: MovieProvider,
    private val randHelper: RandomHelper
) {

    private val lastQuestions: ArrayList<Question> = ArrayList()

    init {
        resetGame()
    }

    fun ask() {
        val q = lastQuestion()
        // println("Last question: $q")

        view.showQuestion(q.description, q.imageUrls[0], q.variants)
    }

    private fun lastQuestion(): Question {
        if (lastQuestions.size > 0 && !lastQuestions.last().result.isAnswered()) {
            return lastQuestions.last()
        }
        lastQuestions.add(createQuestion())

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

        val rightIndex = randHelper.fromRange(0..movies.size)

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
            view.markVariantAsRight(selectedVariant)
            view.showResult("Вы угадали!")
            q.result = AnswerResult.RESULT_RIGHT
        } else {
            view.markVariantAsWrong(selectedVariant)
            view.markVariantAsRight(q.rightVariant)
            view.showResult("Ответ неверный :(")
            q.result = AnswerResult.RESULT_WRONG
        }
    }

    fun resetGame() {
        lastQuestions.clear()
    }

}