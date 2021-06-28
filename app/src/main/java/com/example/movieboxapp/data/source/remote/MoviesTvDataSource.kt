package com.example.movieboxapp.data.source.remote

import androidx.lifecycle.LiveData
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.vo.Resource

interface MoviesTvDataSource {
    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getAllTvshows(): LiveData<Resource<List<TvShowEntity>>>
    fun getDetailMovie(id: Int): LiveData<Resource<DetailEntity>>
    fun getDetailTvshow(id: Int): LiveData<Resource<DetailEntity>>
    fun getBookmarked(): LiveData<List<DetailEntity>>
    fun setBookmark(detailEntity: DetailEntity, state: Boolean)
}