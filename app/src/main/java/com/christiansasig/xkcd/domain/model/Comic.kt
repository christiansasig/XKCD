package com.christiansasig.xkcd.domain.model

import com.christiansasig.xkcd.data.database.entities.ComicEntity
import com.christiansasig.xkcd.data.network.model.ComicModel

data class Comic (
val id: Int,
val num: Int,
val safeTitle: String,
val img: String? = null,
val month: String? = null,
val link: String? = null,
val year: String? = null,
val news: String? = null,
val transcript: String? = null,
val alt: String? = null,
val title: String? = null,
val day: String? = null
)

//extension function/Funcion de extension
fun ComicModel.toDomain() = Comic(id, num, safeTitle, img, month, link, year, news, transcript, alt, title, day)
fun ComicEntity.toDomain() = Comic(id, num, safeTitle, img, month, link, year, news, transcript, alt, title, day)