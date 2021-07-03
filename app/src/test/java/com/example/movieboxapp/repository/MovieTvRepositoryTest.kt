package com.example.movieboxapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.data.source.local.LocalDataSource
import com.example.movieboxapp.data.source.remote.RemoteDataSource
import com.example.movieboxapp.utils.AppExecutors
import com.example.movieboxapp.utils.PagedListUtil
import com.example.movieboxapp.utils.retrofit.LiveTestDataUtil
import com.example.movieboxapp.utils.retrofit.TestData
import com.example.movieboxapp.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MovieTvRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieTvRepositoryTest = FakeMovieTvRepository(remote, local, appExecutors)
    private val movieResponses = TestData.generateRemoteMoviesTest()
    private val tvshowResponses = TestData.generateRemoteTvshowTest()
    private val movieId = movieResponses[0].results[0].id
    private val tvshowId = tvshowResponses[0].results[0].id
    private val detailTvshow = TestData.generateDataDetailTvTest()

    @Test
    fun getAllMovies() {
//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.GetAllMoviesCallback)
//                .getAllMoviesAsync(TestData.generateDataMovie())
//            null
//        }.`when`(remote).getAllMovies(any())
//        val movieEntities = LiveTestDataUtil.getValue(movieTvRepositoryTest.getAllMovies())
//        verify<RemoteDataSource>(remote).getAllMovies(any())
        val dataSourceFactory =
            mock(androidx.paging.DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, MovieEntity>
//        val dummyMovies = MutableLiveData<List<DataMovie>>()
//        dummyMovies.value = TestData.generateDataMovie()
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        movieTvRepositoryTest.getAllMovies()
//        val movieEntities = LiveTestDataUtil.getValue(movieTvRepositoryTest.getAllMovies())
        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(TestData.generateDataMovie()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses[0].results.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvshows() {
//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.GetAllTvshowCallback)
//                .getAllTvshowAsync(TestData.generateDataTvshow())
//            null
//        }.`when`(remote).getAllTvshow(any())
//        val tvshowEntities = LiveTestDataUtil.getValue(movieTvRepositoryTest.getAllTvshows())
//        verify(remote).getAllTvshow(any())
//        val dummyTvshow = MutableLiveData<List<DataTvshow>>()
//        dummyTvshow.value = TestData.generateDataTvshow()
        val dataSourceFactory =
            mock(androidx.paging.DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        movieTvRepositoryTest.getAllTvshows()
//        val tvshowEntities = LiveTestDataUtil.getValue(movieTvRepositoryTest.getAllTvshows())
        val tvshowEntities =
            Resource.success(PagedListUtil.mockPagedList(TestData.generateDataTvshow()))
        verify(local).getAllTvShows()
        assertNotNull(tvshowEntities.data)
        assertEquals(tvshowResponses[0].results.size.toLong(), tvshowEntities.data?.size?.toLong())
    }

    @Test
    fun getBookmarked() {
//            val dummyBookmark = MutableLiveData<DetailEntity>()
//            dummyBookmark.value = TestData.generateDataDetailTvTest()
        val dataSourceFactory =
            mock(androidx.paging.DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, DetailEntity>
        `when`(local.getBookmarked()).thenReturn(dataSourceFactory)
        movieTvRepositoryTest.getBookmarked()
//        val bookmarkEntity = LiveTestDataUtil.getValue(movieTvRepositoryTest.getBookmarked())
        val bookmarkEntity =
            Resource.success(PagedListUtil.mockPagedList(TestData.generateDataTvshowTest()))
        verify(local).getBookmarked()
        assertNotNull(bookmarkEntity.data)
        assertEquals(tvshowResponses[0].results.size.toLong(), bookmarkEntity.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.GetDetailMovieCallback)
//                .getDetailMovieCallback(TestData.generateRemoteDetailMovieTest())
//            null
//        }.`when`(remote).getMovieDetail(any(), eq(movieId))
//        val detailMovieEntity =
//            LiveTestDataUtil.getValue(movieTvRepositoryTest.getDetailMovie(movieId))
//        verify(remote).getMovieDetail(any(), eq(movieId))
        val dummyDetailMovie = MutableLiveData<DetailEntity>()
        dummyDetailMovie.value = TestData.generateDataDetailMovieTest()
        `when`(local.getDetailMovie(movieId.toString())).thenReturn(dummyDetailMovie)
        val detailMovie = LiveTestDataUtil.getValue(movieTvRepositoryTest.getDetailMovie(movieId))
        verify(local).getDetailMovie(movieId.toString())
        assertNotNull(detailMovie)
        assertNotNull(detailMovie.data)
        assertEquals(detailMovie.data, dummyDetailMovie.value)
    }

    @Test
    fun getDetailTvshow() {
//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.GetDetailTvshowCallback)
//                .getDetailTvshowCallback(TestData.generateRemoteDetailTvshowTest())
//            null
//        }.`when`(remote).getTvshowDetail(any(), eq(tvshowId))
//        val detailTvshowEntity =
//            LiveTestDataUtil.getValue(movieTvRepositoryTest.getDetailTvshow(tvshowId))
//        verify(remote).getTvshowDetail(any(), eq(tvshowId))
        val dummyDetailTvShow = MutableLiveData<DetailEntity>()
        dummyDetailTvShow.value = TestData.generateDataDetailTvTest()
        `when`(local.getDetailTvShow(tvshowId.toString())).thenReturn(dummyDetailTvShow)
        val detailTvShow =
            LiveTestDataUtil.getValue(movieTvRepositoryTest.getDetailTvshow(tvshowId))
        verify(local).getDetailTvShow(tvshowId.toString())
        assertNotNull(detailTvShow)
        assertNotNull(detailTvShow.data)
        assertEquals(detailTvshow, dummyDetailTvShow.value)
    }


}