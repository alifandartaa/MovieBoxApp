package com.example.movieboxapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.data.source.remote.response.DataMovie
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.ui.movies.MoviesViewModel
import com.example.movieboxapp.utils.retrofit.TestData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModelMovie: DetailViewModel
    private lateinit var viewModelTvshow: DetailViewModel
    private val testTvshow = TestData.generateDataDetailTvTest()
    private val tvIdTest = testTvshow.id
    private val testMovie = TestData.generateDataDetailMovieTest()
    private val movieIdTest = testMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepositoryForMov: MovieTvRepository

    @Mock
    private lateinit var movieTvRepositoryForTv: MovieTvRepository

    @Before
    fun setUp() {
        viewModelMovie = DetailViewModel(movieTvRepositoryForMov)
        viewModelMovie.selectId(movieIdTest)
        viewModelTvshow = DetailViewModel(movieTvRepositoryForTv)
        viewModelTvshow.selectId(tvIdTest)
    }

    @Mock
    private lateinit var movieObserver: Observer<DetailEntity>

    @Mock
    private lateinit var tvshoweObserver: Observer<DetailEntity>

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<DetailEntity>()
        movie.value = testMovie
        `when`(movieIdTest.let { movieTvRepositoryForMov.getDetailMovie(it) }).thenReturn(movie)
        val detailEntity = viewModelMovie.getDetailMovie()!!.value as DetailEntity
        verify(movieTvRepositoryForMov).getDetailMovie(movieIdTest)
        assertNotNull(detailEntity)
        assertEquals(testMovie.id, detailEntity.id)
        assertEquals(testMovie.title, detailEntity.title)
        assertEquals(testMovie.description, detailEntity.description)
        assertEquals(testMovie.imagePath, detailEntity.imagePath)
        assertEquals(testMovie.releaseDate, detailEntity.releaseDate)
        assertEquals(testMovie.status, detailEntity.status)
        assertEquals(testMovie.tagline, detailEntity.tagline)

        viewModelMovie.getDetailMovie()!!.observeForever(movieObserver)
        verify(movieObserver).onChanged(testMovie)
    }

    @Test
    fun getDetailTvshow() {
        val tvshow = MutableLiveData<DetailEntity>()
        tvshow.value = testTvshow
        `when`(tvIdTest.let { movieTvRepositoryForTv.getDetailTvshow(it) }).thenReturn(tvshow)
        val detailEntity = viewModelTvshow.getDetailTvshow()!!.value as DetailEntity
        verify(movieTvRepositoryForTv).getDetailTvshow(tvIdTest)
        assertNotNull(detailEntity)
        assertEquals(testTvshow.id, detailEntity.id)
        assertEquals(testTvshow.title, detailEntity.title)
        assertEquals(testTvshow.description, detailEntity.description)
        assertEquals(testTvshow.imagePath, detailEntity.imagePath)
        assertEquals(testTvshow.releaseDate, detailEntity.releaseDate)
        assertEquals(testTvshow.status, detailEntity.status)
        assertEquals(testTvshow.tagline, detailEntity.tagline)

        viewModelTvshow.getDetailTvshow()!!.observeForever(tvshoweObserver)
        verify(tvshoweObserver).onChanged(testTvshow)
    }
}