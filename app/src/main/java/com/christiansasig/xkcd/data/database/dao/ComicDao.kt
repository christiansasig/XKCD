package com.christiansasig.xkcd.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.christiansasig.xkcd.data.database.entities.ComicEntity

@Dao
interface ComicDao {

    @Query("SELECT * FROM comic_table ORDER BY num DESC")
    suspend fun getAll():List<ComicEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<ComicEntity>)

    @Query("DELETE FROM comic_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM comic_table WHERE id = :id")
    suspend fun getById(id:Int): ComicEntity

    @Query("SELECT * FROM comic_table WHERE num = :num")
    suspend fun getByNum(num:Int): ComicEntity
}