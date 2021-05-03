package com.example.movieboxapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.data.source.remote.MoviesTvDataSource
import com.example.movieboxapp.data.source.remote.RemoteDataSource
import com.example.movieboxapp.data.source.remote.response.DataMovie
import com.example.movieboxapp.data.source.remote.response.DataTvshow
import com.example.movieboxapp.data.source.remote.response.ResponseDetailMovie
import com.example.movieboxapp.data.source.remote.response.ResponseDetailTvshow

class MovieTvRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MoviesTvDataSource {


    companion object {
        @Volatile
        private var instance: MovieTvRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MovieTvRepository = instance
            ?: synchronized(this) {
                MovieTvRepository(remoteData).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val moviesResult = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.GetAllMoviesCallback {
            override fun getAllMoviesAsync(movies: List<DataMovie>?) {
                val moviesList = ArrayList<MovieEntity>()
                if (movies != null) {
                    for (response in movies) {
                        val movie = response.let {
                            MovieEntity(
                                it.id,
                                it.title,
                                it.overview,
                                it.posterPath
                            )
                        }
                        moviesList.add(movie)
                    }
                }
                moviesResult.postValue(moviesList)
            }
        })
        return moviesResult
    }

    override fun getAllTvshows(): LiveData<List<TvShowEntity>> {
        val tvshowsResult = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getAllTvshow(object : RemoteDataSource.GetAllTvshowCallback {
            override fun getAllTvshowAsync(tvshows: List<DataTvshow>?) {
                val tvshowsList = ArrayList<TvShowEntity>()
                if (tvshows != null) {
                    for (response in tvshows) {
                        val tvshow = response.let {
                            TvShowEntity(
                                it.id,
                                it.name,
                                it.overview,
                                it.posterPath
                            )
                        }
                        tvshowsList.add(tvshow)
                    }
                }
                tvshowsResult.postValue(tvshowsList)
            }
        })
        return tvshowsResult
    }


    override fun getDetailMovie(id: Int): LiveData<DetailEntity> {
        val movieDetailResult = MutableLiveData<DetailEntity>()
        remoteDataSource.getMovieDetail(object : RemoteDataSource.GetDetailMovieCallback {
            override fun getDetailMovieCallback(movie: ResponseDetailMovie?) {
                val movieDetail = movie?.let {
                    DetailEntity(
                        movie.id,
                        movie.title,
                        movie.overview,
                        movie.posterPath,
                        movie.releaseDate,
                        movie.status,
                        movie.tagline
                    )
                }
                movieDetailResult.postValue(movieDetail!!)
            }
        }, id)
        return movieDetailResult
    }

    override fun getDetailTvshow(id: Int): LiveData<DetailEntity> {
        val tvshowDetailResult = MutableLiveData<DetailEntity>()
        remoteDataSource.getTvshowDetail(object : RemoteDataSource.GetDetailTvshowCallback {
            override fun getDetailTvshowCallback(tvshow: ResponseDetailTvshow?) {
                val tvshowDetail = tvshow?.let {
                    DetailEntity(
                        tvshow.id,
                        tvshow.name,
                        tvshow.overview,
                        tvshow.posterPath,
                        tvshow.firstAirDate,
                        tvshow.status,
                        tvshow.tagline
                    )
                }
                tvshowDetailResult.postValue(tvshowDetail!!)
            }
        }, id)
        return tvshowDetailResult
    }


}