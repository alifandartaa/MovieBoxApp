package com.example.movieboxapp.data.source.remote

import androidx.lifecycle.LiveData
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.data.source.entity.TvShowEntity

interface MoviesTvDataSource {
    fun getAllMovies(): LiveData<List<MovieEntity>>
    fun getAllTvshows(): LiveData<List<TvShowEntity>>
    fun getDetailMovie(id: Int): LiveData<DetailEntity>
    fun getDetailTvshow(id: Int): LiveData<DetailEntity>
}