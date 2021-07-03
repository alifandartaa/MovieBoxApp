package com.example.movieboxapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.utils.retrofit.TestData
import com.example.movieboxapp.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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
    private lateinit var movieObserver: Observer<Resource<DetailEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<DetailEntity>>

    @Test
    fun getDetailMovie() {
        val expected = MutableLiveData<Resource<DetailEntity>>()
        expected.value = Resource.success(testMovie)
        `when`(movieTvRepositoryForMov.getDetailMovie(movieIdTest)).thenReturn(expected)
        viewModelMovie.detailMovie.observeForever(movieObserver)
        verify(movieObserver).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModelMovie.detailMovie.value
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun getDetailTvshow() {
        val expected = MutableLiveData<Resource<DetailEntity>>()
        expected.value = Resource.success(testTvshow)
        `when`(movieTvRepositoryForTv.getDetailTvshow(tvIdTest)).thenReturn(expected)
        viewModelTvshow.detailTvShow.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModelTvshow.detailTvShow.value
        assertEquals(expectedValue, actualValue)
    }
}