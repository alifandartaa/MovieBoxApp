package com.example.movieboxapp.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieTvRepository)
    }

    @Test
    fun getTvshow() {
        val dummyTvShow = Resource.success(pagedList)
        `when`(dummyTvShow.data?.size).thenReturn(5)
        val tvshows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvshows.value = dummyTvShow

        `when`(movieTvRepository.getAllTvshows()).thenReturn(tvshows)
        val tvshowEntities = viewModel.getTvshow().value?.data
        verify(movieTvRepository).getAllTvshows()
        assertNotNull(tvshowEntities)
        assertEquals(5, tvshowEntities!!.size)

        viewModel.getTvshow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}