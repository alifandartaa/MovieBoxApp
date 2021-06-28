package com.example.movieboxapp.di

import android.content.Context
import com.example.movieboxapp.data.source.local.LocalDataSource
import com.example.movieboxapp.data.source.remote.RemoteDataSource
import com.example.movieboxapp.data.source.room.MovieTvDatabase
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.utils.AppExecutors

object Injection {
    fun provideMoviesRepository(context: Context): MovieTvRepository {

        val database = MovieTvDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()

        val localDataSource = LocalDataSource.getInstance(database.movieTvDao())
        val appExecutors = AppExecutors()

        return MovieTvRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}