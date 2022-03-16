package com.christiansasig.androidhiltmvvmarchitecture.di

import android.content.Context
import com.christiansasig.xkcd.data.network.ComicApiClient
import com.christiansasig.xkcd.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext appContext: Context):Retrofit{
        return Retrofit.Builder()
            .baseUrl(appContext.getString(R.string.server_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApiClient(retrofit: Retrofit): ComicApiClient {
        return retrofit.create(ComicApiClient::class.java)
    }
}