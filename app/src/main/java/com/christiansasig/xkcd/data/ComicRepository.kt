package com.christiansasig.xkcd.data

import com.christiansasig.xkcd.data.database.dao.ComicDao
import com.christiansasig.xkcd.data.database.entities.ComicEntity
import com.christiansasig.xkcd.data.network.model.ComicModel
import com.christiansasig.xkcd.domain.model.Comic
import com.christiansasig.xkcd.data.network.ComicService
import com.christiansasig.xkcd.domain.model.toDomain

import javax.inject.Inject

class ComicRepository @Inject constructor(
    private val api: ComicService,
    private val comicDao: ComicDao
) {

    suspend fun getCurrentComicFromApi(): Comic? {
        val response: ComicModel? = api.getCurrentComic()
        return response?.toDomain()
    }

    suspend fun getComicByIdFromApi(id: Int): Comic? {
        val response: ComicModel? = api.getComicById(id)
        return response?.toDomain()
    }

    suspend fun getAllComicsFromDatabase():List<Comic>{
        val response: List<ComicEntity> = comicDao.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun getComicFromDatabaseByNum(num: Int): Comic {
        val response: ComicEntity = comicDao.getByNum(num)
        return response.toDomain()
    }

    suspend fun insertComics(entities:List<ComicEntity>){
        comicDao.insertAll(entities)
    }

    suspend fun clearComics(){
        comicDao.deleteAll()
    }
}