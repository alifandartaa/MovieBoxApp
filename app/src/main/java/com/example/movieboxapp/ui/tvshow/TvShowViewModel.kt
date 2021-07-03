package com.example.movieboxapp.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.vo.Resource

class TvShowViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    fun getTvshow(): LiveData<Resource<PagedList<TvShowEntity>>> = movieTvRepository.getAllTvshows()
}