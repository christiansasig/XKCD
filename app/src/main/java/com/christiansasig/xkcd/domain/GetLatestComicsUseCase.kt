package com.christiansasig.xkcd.domain

import com.christiansasig.xkcd.data.ComicRepository
import com.christiansasig.xkcd.data.database.entities.toDatabase
import com.christiansasig.xkcd.domain.model.Comic
import javax.inject.Inject

class GetLatestComicsUseCase @Inject constructor(private val repository: ComicRepository) {
    private val totalItems:Int = 25

    suspend operator fun invoke():List<Comic>{
        var comics = mutableListOf<Comic>()
        val currentComic = repository.getCurrentComicFromApi()
        if(currentComic != null){
            val number = currentComic.num - 1
            comics.add(currentComic)
            for (id in number downTo number - (totalItems - 2)) {
                val comic = repository.getComicByIdFromApi(id)
                if(comic != null){
                    comics.add(comic)
                }
            }
            repository.clearComics()
            repository.insertComics(comics.map { it.toDatabase() })
        }else{
            comics = repository.getAllComicsFromDatabase().toMutableList()
        }
        return comics
    }
}