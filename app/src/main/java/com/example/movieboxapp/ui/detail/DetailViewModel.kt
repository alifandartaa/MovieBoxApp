package com.example.movieboxapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.data.source.entity.DetailEntity

class DetailViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    private var id: Int? = 0

    fun selectId(id: Int) {
        this.id = id
    }

    fun getDetailMovie(): LiveData<DetailEntity>? = id?.let { movieTvRepository.getDetailMovie(it) }

    fun getDetailTvshow(): LiveData<DetailEntity>? =
        id?.let { movieTvRepository.getDetailTvshow(it) }
}