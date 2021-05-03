package com.example.movieboxapp.ui.movies

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.movieboxapp.R
import com.example.movieboxapp.ui.HomeActivity
import com.example.movieboxapp.utils.retrofit.TestData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class MoviesFragmentTest {

    private val dataMoviesTest = TestData.generateDataMoviesTest()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadPopularMovies(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataMoviesTest.size))
    }
}