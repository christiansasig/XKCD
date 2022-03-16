package com.christiansasig.xkcd.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.christiansasig.xkcd.data.database.dao.ComicDao
import com.christiansasig.xkcd.data.database.entities.ComicEntity

@Database(entities = [ComicEntity::class], version = 1)
abstract class ComicDatabase: RoomDatabase() {
    abstract fun getComicDao(): ComicDao
}