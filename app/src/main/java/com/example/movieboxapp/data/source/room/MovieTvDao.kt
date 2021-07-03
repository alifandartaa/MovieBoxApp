package com.example.movieboxapp.data.source.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.data.source.entity.TvShowEntity

@Dao
interface MovieTvDao {
    @Query("SELECT * FROM movieEntities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM detailEntities WHERE detailId = :movieId")
    fun getDetailMovieById(movieId: String): LiveData<DetailEntity>

    @Query("SELECT * FROM tvShowEntities")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM detailEntities WHERE detailId = :tvShowId")
    fun getDetailTvShowById(tvShowId: String): LiveData<DetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(detailEntity: DetailEntity)

    @Query("SELECT * FROM detailEntities WHERE bookmarked = 1")
    fun getBookmarked(): DataSource.Factory<Int, DetailEntity>

    @Update
    fun updateDetail(detail: DetailEntity)
}