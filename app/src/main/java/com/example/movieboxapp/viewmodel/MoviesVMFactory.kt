package com.example.movieboxapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.di.Injection
import com.example.movieboxapp.ui.detail.DetailViewModel
import com.example.movieboxapp.ui.movies.MoviesViewModel
import com.example.movieboxapp.ui.tvshow.TvShowViewModel

class MoviesVMFactory private constructor(private val mMovieTvRepository: MovieTvRepository) :
        ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MoviesVMFactory? = null
        fun getInstance(context: Context): MoviesVMFactory = instance ?: synchronized(this) {
            MoviesVMFactory(Injection.provideMoviesRepository(context)).apply {
                instance = this
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(mMovieTvRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mMovieTvRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mMovieTvRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}