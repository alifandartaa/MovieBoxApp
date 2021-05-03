package com.example.movieboxapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieboxapp.data.source.remote.RemoteDataSource
import com.example.movieboxapp.data.source.remote.response.ResponseMovie
import com.example.movieboxapp.utils.retrofit.LiveTestDataUtil
import com.example.movieboxapp.utils.retrofit.TestData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.Mockito.*

class MovieTvRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieTvRepositoryTest = FakeMovieTvRepository(remote)
    private val movieResponses = TestData.generateRemoteMoviesTest()
    private val tvshowResponses = TestData.generateRemoteTvshowTest()
    private val movieId = movieResponses[0].results[0].id
    private val tvshowId = tvshowResponses[0].results[0].id
    private val detailMovie = TestData.generateDataDetailMovieTest()
    private val detailTvshow = TestData.generateDataDetailTvTest()

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.GetAllMoviesCallback)
                .getAllMoviesAsync(TestData.generateDataMovie())
            null
        }.`when`(remote).getAllMovies(any())
        val movieEntities = LiveTestDataUtil.getValue(movieTvRepositoryTest.getAllMovies())
        verify<RemoteDataSource>(remote).getAllMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses[0].results.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTvshows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.GetAllTvshowCallback)
                .getAllTvshowAsync(TestData.generateDataTvshow())
            null
        }.`when`(remote).getAllTvshow(any())
        val tvshowEntities = LiveTestDataUtil.getValue(movieTvRepositoryTest.getAllTvshows())
        verify(remote).getAllTvshow(any())
        assertNotNull(tvshowEntities)
        assertEquals(tvshowResponses[0].results.size.toLong(), tvshowEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.GetDetailMovieCallback)
                .getDetailMovieCallback(TestData.generateRemoteDetailMovieTest())
            null
        }.`when`(remote).getMovieDetail(any(), eq(movieId))
        val detailMovieEntity =
            LiveTestDataUtil.getValue(movieTvRepositoryTest.getDetailMovie(movieId))
        verify(remote).getMovieDetail(any(), eq(movieId))
        assertNotNull(detailMovieEntity)
        assertEquals(detailMovie, detailMovieEntity)
    }

    @Test
    fun getDetailTvshow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.GetDetailTvshowCallback)
                .getDetailTvshowCallback(TestData.generateRemoteDetailTvshowTest())
            null
        }.`when`(remote).getTvshowDetail(any(), eq(tvshowId))
        val detailTvshowEntity =
            LiveTestDataUtil.getValue(movieTvRepositoryTest.getDetailTvshow(tvshowId))
        verify(remote).getTvshowDetail(any(), eq(tvshowId))
        assertNotNull(detailTvshowEntity)
        assertEquals(detailTvshow, detailTvshowEntity)
    }
}