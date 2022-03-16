package com.christiansasig.xkcd.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.christiansasig.xkcd.domain.model.Comic

@Entity(tableName = "comic_table")
data class ComicEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "num") val num: Int,
    @ColumnInfo(name = "month") val month: String?,
    @ColumnInfo(name = "link") val link: String?,
    @ColumnInfo(name = "year") val year: String?,
    @ColumnInfo(name = "news") val news: String?,
    @ColumnInfo(name = "safe_title") val safeTitle: String,
    @ColumnInfo(name = "transcript") val transcript: String?,
    @ColumnInfo(name = "alt") val alt: String?,
    @ColumnInfo(name = "img") val img: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "day") val day: String?
)

fun Comic.toDatabase() = ComicEntity(num = num, month =  month, link = link, year = year, news = news, safeTitle = safeTitle, transcript = transcript, alt = alt, img = img, title = title, day = day)