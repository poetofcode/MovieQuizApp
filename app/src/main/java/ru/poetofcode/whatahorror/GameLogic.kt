package ru.poetofcode.whatahorror

class GameLogic(private val view: IView) {

    private val lastQuestions: ArrayList<Question> = ArrayList()

    init {
        resetGame()
    }

    fun ask() {
        val q = getCurrQuestion()
        view.showQuestion(q.description, q.imageUrls[0], q.variants)
    }

    private fun getCurrQuestion(): Question {
        if (lastQuestions.size > 0 && lastQuestions.last().result.isAnswered()) {
            return lastQuestions.last()
        }
        lastQuestions.add(createQuestion())

        return lastQuestions.last()
    }

    private fun createQuestion(): Question {

        // TODO implement logic of creation new question:
        //  ..
        //  1. Create new interface MovieProvider
        //  2. Define methods for getting data for building question

        return Question(
            description = "Test movie name",
            imageUrls = listOf(),
            variants = listOf(),
            indexOfRightVariant = 0
        )
    }

    fun reply(selectedVariant: Int) {
        val q = getCurrQuestion()

        if (selectedVariant == q.indexOfRightVariant) {
            view.markVariantAsRight(selectedVariant)
            view.showResult("Вы угадали!")
        } else {
            view.markVariantAsWrong(selectedVariant)
            view.markVariantAsRight(q.indexOfRightVariant)
            view.showResult("Ответ неверный :(")
        }
    }

    fun resetGame() {
        lastQuestions.clear()
    }

}