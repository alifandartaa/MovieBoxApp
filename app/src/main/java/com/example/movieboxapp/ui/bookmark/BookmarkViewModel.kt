package com.example.movieboxapp.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.repository.MovieTvRepository

class BookmarkViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    fun getBookmark(): LiveData<PagedList<DetailEntity>> {
        return movieTvRepository.getBookmarked()
    }

    fun setBookmark(detailEntity: DetailEntity) {
        val newState = !detailEntity.bookmarked
        movieTvRepository.setBookmark(detailEntity, newState)
    }
}