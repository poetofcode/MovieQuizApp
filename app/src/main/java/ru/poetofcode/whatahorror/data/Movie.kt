package ru.poetofcode.whatahorror.data

import com.google.gson.annotations.SerializedName

data class Movie(val name: String, @SerializedName("images") val imageUrls: List<String>)