package ru.poetofcode.whatahorror.presentation

data class QuestionInfo(
    val description: String,
    val variants: List<String> = listOf()
)