package com.example.movieboxapp.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.repository.MovieTvRepository

class TvShowViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    fun getTvshow(): LiveData<List<TvShowEntity>> = movieTvRepository.getAllTvshows()
}