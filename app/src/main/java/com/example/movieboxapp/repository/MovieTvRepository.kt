package com.example.movieboxapp.repository

import androidx.lifecycle.LiveData
import com.example.movieboxapp.data.source.NetworkBoundSource
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.data.source.local.LocalDataSource
import com.example.movieboxapp.data.source.remote.ApiResponse
import com.example.movieboxapp.data.source.remote.MoviesTvDataSource
import com.example.movieboxapp.data.source.remote.RemoteDataSource
import com.example.movieboxapp.data.source.remote.response.ResponseDetailMovie
import com.example.movieboxapp.data.source.remote.response.ResponseDetailTvshow
import com.example.movieboxapp.data.source.remote.response.ResponseMovie
import com.example.movieboxapp.data.source.remote.response.ResponseTvshow
import com.example.movieboxapp.utils.AppExecutors
import com.example.movieboxapp.vo.Resource

class MovieTvRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MoviesTvDataSource {


    companion object {
        @Volatile
        private var instance: MovieTvRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieTvRepository = instance
            ?: synchronized(this) {
                MovieTvRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundSource<List<MovieEntity>, ResponseMovie>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getAllMovies()


            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<ResponseMovie>> =
                remoteDataSource.getAllMovies()


            override fun saveCallResult(data: ResponseMovie) {
                val moviesList = ArrayList<MovieEntity>()
                val current = data.results
                for (response in current) {
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
                localDataSource.insertMovies(moviesList)
            }

        }.asLiveData()
    }

    override fun getAllTvshows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundSource<List<TvShowEntity>, ResponseTvshow>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getAllTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<ResponseTvshow>> =
                remoteDataSource.getAllTvshow()


            override fun saveCallResult(data: ResponseTvshow) {
                val tvshowsList = ArrayList<TvShowEntity>()
                val current = data.results
                for (response in current) {
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
                localDataSource.insertTvShow(tvshowsList)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(id: Int): LiveData<Resource<DetailEntity>> {
        return object : NetworkBoundSource<DetailEntity, ResponseDetailMovie>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailEntity> =
                localDataSource.getDetailMovie(id.toString())


            override fun shouldFetch(data: DetailEntity?): Boolean =
                data == null


            override fun createCall(): LiveData<ApiResponse<ResponseDetailMovie>> =
                remoteDataSource.getMovieDetail(id)


            override fun saveCallResult(data: ResponseDetailMovie) {
                val movieDetail = DetailEntity(
                    data.id,
                    data.title,
                    data.overview,
                    data.posterPath,
                    data.releaseDate,
                    data.status,
                    data.tagline
                )
                localDataSource.insertDetail(movieDetail)
            }

        }.asLiveData()
    }

    override fun getDetailTvshow(id: Int): LiveData<Resource<DetailEntity>> {
        return object : NetworkBoundSource<DetailEntity, ResponseDetailTvshow>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailEntity> =
                localDataSource.getDetailTvShow(id.toString())


            override fun shouldFetch(data: DetailEntity?): Boolean =
                data == null


            override fun createCall(): LiveData<ApiResponse<ResponseDetailTvshow>> =
                remoteDataSource.getTvshowDetail(id)


            override fun saveCallResult(data: ResponseDetailTvshow) {
                val tvshowDetail = DetailEntity(
                    data.id,
                    data.name,
                    data.overview,
                    data.posterPath,
                    data.firstAirDate,
                    data.status,
                    data.tagline
                )
                localDataSource.insertDetail(tvshowDetail)
            }

        }.asLiveData()
    }

    override fun getBookmarked(): LiveData<List<DetailEntity>> =
        localDataSource.getBookmarked()

    override fun setBookmark(detailEntity: DetailEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.setBookmark(detailEntity, state)
        }

}