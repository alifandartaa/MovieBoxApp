package com.example.movieboxapp.di

import android.content.Context
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.data.source.remote.RemoteDataSource

object Injection {
    fun provideMoviesRepository(context: Context): MovieTvRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieTvRepository.getInstance(remoteDataSource)
    }
}