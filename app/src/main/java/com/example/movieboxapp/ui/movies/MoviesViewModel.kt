package com.example.movieboxapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.data.source.entity.MovieEntity

class MoviesViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>> = movieTvRepository.getAllMovies()
}