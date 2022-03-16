package com.christiansasig.xkcd.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christiansasig.xkcd.domain.GetComicUseCase
import com.christiansasig.xkcd.domain.GetLatestComicsUseCase
import com.christiansasig.xkcd.domain.model.Comic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val getLatestComicsUseCase: GetLatestComicsUseCase,
    private val getComicUseCase: GetComicUseCase
) : ViewModel() {

    val comicModel = MutableLiveData<List<Comic>>()
    val isLoading = MutableLiveData<Boolean>()
    val comic = MutableLiveData<Comic>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getLatestComicsUseCase()

            if (!result.isNullOrEmpty()) {
                comicModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun getComicByNum(num: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            comic.postValue( getComicUseCase.getComicFromDatabaseByNum(num))
        }
    }
}