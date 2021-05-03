package com.example.movieboxapp.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.utils.retrofit.TestData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MovieTvRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(movieTvRepository)
    }

    @Test
    fun getTvshow() {
        val dataTestTvshow = TestData.generateDataTvshowTest()
        val tvshows = MutableLiveData<List<TvShowEntity>>()
        tvshows.value = dataTestTvshow

        `when`(movieTvRepository.getAllTvshows()).thenReturn(tvshows)
        val tvshowEntities = viewModel.getTvshow().value
        verify(movieTvRepository).getAllTvshows()
        assertNotNull(tvshowEntities)
        assertEquals(20, tvshowEntities!!.size)

        viewModel.getTvshow().observeForever(observer)
        verify(observer).onChanged(dataTestTvshow)
    }
}