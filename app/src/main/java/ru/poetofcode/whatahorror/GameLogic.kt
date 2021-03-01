package ru.poetofcode.whatahorror

class GameLogic(val view: IView) {

    val lastQuestions: ArrayList<Question> = ArrayList()

    init {
        resetGame()
    }

    fun ask() {
        val q = getCurrQuestion()
        view.showQuestion(q.description, q.imageUrls[0], q.variants)
    }

    fun getCurrQuestion(): Question {
        if (lastQuestions.size > 0 && lastQuestions.last().result.isAnswered()) {
            return lastQuestions.last()
        }
        lastQuestions.add(createQuestion())

        return lastQuestions.last()
    }

    private fun createQuestion(): Question {

        // TODO implement logic of creation new question

        return Question(
            description = "Test movie name",
            imageUrls = listOf(),
            variants = listOf(),
            indexOfRightVariant = 0
        )
    }

    fun reply(id: Int): Boolean {

        // TODO implement it

        return false
    }

    fun resetGame() {
        lastQuestions.clear()
    }

}