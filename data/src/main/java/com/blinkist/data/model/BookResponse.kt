package com.blinkist.data.model

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate

data class BookResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("publishDate")
    val publishDate: LocalDate,
    @SerializedName("coverImageUrl")
    val coverImageUrl: String
)
