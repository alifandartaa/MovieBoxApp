package com.example.movieboxapp.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.utils.retrofit.TestData
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MovieTvRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(movieTvRepository)
    }

    @Test
    fun getMovies() {
        val dataTestMovies = TestData.generateDataMoviesTest()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dataTestMovies

        `when`(movieTvRepository.getAllMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        verify(movieTvRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities!!.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dataTestMovies)
    }
}