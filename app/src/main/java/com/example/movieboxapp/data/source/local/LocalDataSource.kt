package com.example.movieboxapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.data.source.room.MovieTvDao

class LocalDataSource private constructor(private val mMovieTvDao: MovieTvDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieTvDao: MovieTvDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieTvDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mMovieTvDao.getMovies()

    fun getDetailMovie(movieId: String): LiveData<DetailEntity> =
        mMovieTvDao.getDetailMovieById(movieId)

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = mMovieTvDao.getTvShows()

    fun getDetailTvShow(tvShowId: String): LiveData<DetailEntity> =
        mMovieTvDao.getDetailTvShowById(tvShowId)

    fun insertMovies(movies: List<MovieEntity>) = mMovieTvDao.insertMovies(movies)

    fun insertTvShow(tvShow: List<TvShowEntity>) = mMovieTvDao.insertTvShow(tvShow)

    fun insertDetail(detail: DetailEntity) = mMovieTvDao.insertDetail(detail)

    fun getBookmarked(): DataSource.Factory<Int, DetailEntity> = mMovieTvDao.getBookmarked()

    fun setBookmark(detailEntity: DetailEntity, newState: Boolean) {
        detailEntity.bookmarked = newState
        mMovieTvDao.updateDetail(detailEntity)
    }
}