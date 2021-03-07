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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        if (description != other.description) return false
        if (imageUrls != other.imageUrls) return false
        if (variants != other.variants) return false
        if (rightVariant != other.rightVariant) return false
        if (result != other.result) return false

        return true
    }

    override fun hashCode(): Int {
        var result1 = description.hashCode()
        result1 = 31 * result1 + imageUrls.hashCode()
        result1 = 31 * result1 + variants.hashCode()
        result1 = 31 * result1 + rightVariant.hashCode()
        result1 = 31 * result1 + result.hashCode()
        return result1
    }
}