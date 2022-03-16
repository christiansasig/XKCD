package com.christiansasig.xkcd.domain

import com.christiansasig.xkcd.data.ComicRepository
import com.christiansasig.xkcd.domain.model.Comic
import javax.inject.Inject

class GetComicUseCase @Inject constructor(private val repository: ComicRepository) {

    suspend fun getComicFromDatabaseByNum(num: Int): Comic {
        return repository.getComicFromDatabaseByNum(num)
    }
}