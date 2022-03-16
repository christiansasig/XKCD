package com.christiansasig.xkcd.data.network

import com.christiansasig.xkcd.data.network.model.ComicModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class ComicService @Inject constructor(private val api: ComicApiClient) {

    suspend fun getCurrentComic(): ComicModel? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getCurrentComic()
                response.body()
            }catch (e:Exception){
                e.printStackTrace()
                null
            }
        }
    }
    suspend fun getComicById(id:Int): ComicModel? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getById(id)
                response.body()
            }catch (e:Exception){
                e.printStackTrace()
                null
            }
        }
    }
}