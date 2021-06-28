package com.example.movieboxapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.vo.Resource

class MoviesViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<List<MovieEntity>>> = movieTvRepository.getAllMovies()
}