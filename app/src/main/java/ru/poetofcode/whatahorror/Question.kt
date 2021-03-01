package ru.poetofcode.whatahorror

enum class AnswerResult {
    RESULT_NONE,
    RESULT_RIGHT,
    RESULT_WRONG;

    fun isAnswered(): Boolean {
        return this != RESULT_NONE
    }

    fun isRight(): Boolean {
        return this == RESULT_RIGHT
    }
}

data class Question(
    val description: String,
    val imageUrls: List<String>,
    val variants: List<String>,
    val indexOfRightVariant: Int,
    var result: AnswerResult = AnswerResult.RESULT_NONE
)