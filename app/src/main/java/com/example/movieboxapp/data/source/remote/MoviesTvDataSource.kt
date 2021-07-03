package com.example.movieboxapp.data.source.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.vo.Resource

interface MoviesTvDataSource {
    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getAllTvshows(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getDetailMovie(id: Int): LiveData<Resource<DetailEntity>>
    fun getDetailTvshow(id: Int): LiveData<Resource<DetailEntity>>
    fun getBookmarked(): LiveData<PagedList<DetailEntity>>
    fun setBookmark(detailEntity: DetailEntity, state: Boolean)
}