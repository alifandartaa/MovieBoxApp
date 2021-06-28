package com.example.movieboxapp.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.repository.MovieTvRepository

class BookmarkViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    fun getBookmark(): LiveData<List<DetailEntity>> {
        return movieTvRepository.getBookmarked()
    }
}