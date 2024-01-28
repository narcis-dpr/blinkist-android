package com.blinkist.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate

@Entity(tableName = "Books")
data class BookEntity (
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name ="publishDate")
    val publishDate: LocalDate,
    @ColumnInfo(name = "coverImageUrl")
    val coverImageUrl: String
    )