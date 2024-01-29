package com.blinkist.data.model

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate

data class BookResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("publish_date")
    val publishDate: String,
    @SerializedName("cover_image_url")
    val coverImageUrl: String,
    @SerializedName("id")
    val id: String,
)
