package com.example.movieboxapp.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.movieboxapp.R
import com.example.movieboxapp.ui.movies.MoviesFragment
import com.example.movieboxapp.ui.tvshow.TvShowFragment
import com.example.movieboxapp.utils.retrofit.TestData
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dataMoviesTest = TestData.generateDataMoviesTest()
    private val dataTvshowTest = TestData.generateDataTvshowTest()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadPopularMovies() {
        launchFragmentInContainer<MoviesFragment>()
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataMoviesTest.size
            )
        )
    }

    @Test
    fun loadPopularTvshow() {
        launchFragmentInContainer<TvShowFragment>()
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataTvshowTest.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        launchFragmentInContainer<MoviesFragment>()
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        delayTwoSecond()
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dataMoviesTest[0].title.toString())))
    }

    @Test
    fun loadDetailTvshow() {
        launchFragmentInContainer<TvShowFragment>()
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        delayTwoSecond()
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dataTvshowTest[0].title.toString())))
    }

    private fun delayTwoSecond() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}