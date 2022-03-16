package com.christiansasig.xkcd.data.network

import com.christiansasig.xkcd.data.network.model.ComicModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicApiClient {
    @GET("info.0.json")
    suspend fun getCurrentComic(): Response<ComicModel>

    @GET("{id}/info.0.json")
    suspend fun getById(@Path("id") id: Int): Response<ComicModel>
}