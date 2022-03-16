package com.christiansasig.xkcd.data.network.model

import com.google.gson.annotations.SerializedName

data class ComicModel(
    @SerializedName("id") val id: Int,
    @SerializedName("num") val num: Int,
    @SerializedName("month") val month: String?,
    @SerializedName("link") val link: String?,
    @SerializedName("year") val year: String?,
    @SerializedName("news") val news: String?,
    @SerializedName("safe_title") val safeTitle: String,
    @SerializedName("transcript") val transcript: String?,
    @SerializedName("alt") val alt: String?,
    @SerializedName("img") val img: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("day") val day: String?
)