package ru.poetofcode.whatahorror.usecase

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
    val rightVariant: String,
    var result: AnswerResult = AnswerResult.RESULT_NONE
) {
    override fun toString(): String {
        return "Question(description='$description', imageUrls=$imageUrls, variants=$variants, indexOfRightVariant=$rightVariant, result=$result)"
    }
}