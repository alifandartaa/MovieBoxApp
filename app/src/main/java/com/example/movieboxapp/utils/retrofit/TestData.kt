package com.example.movieboxapp.utils.retrofit

import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.data.source.remote.response.DataMovie
import com.example.movieboxapp.data.source.remote.response.DataTvshow
import com.example.movieboxapp.data.source.remote.response.ResponseMovie
import com.example.movieboxapp.data.source.remote.response.ResponseTvshow

object TestData {
    fun generateDataMoviesTest(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        for (i in 1..20) {
            movies.add(
                MovieEntity(
                    460465,
                    "Cruella",
                    "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                    "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg"
                )
            )
        }
        return movies
    }

    fun generateDataTvshowTest(): List<TvShowEntity> {
        val tvshows = ArrayList<TvShowEntity>()
        for (i in 1..20) {
            tvshows.add(
                TvShowEntity(
                    460465,
                    "Game of Thrones",
                    "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                    "/6kbAMLteGO8yyewYau6bJ683sw7.jpg"
                )
            )
        }
        return tvshows
    }

    fun generateDataDetailMovieTest(): DetailEntity {
        return DetailEntity(
            460465,
            "Cruella",
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
            "2021-04-07",
            "Released",
            "Get over here."
        )
    }

    fun generateDataDetailTvTest(): DetailEntity {
        return DetailEntity(
            88396,
            "Game of Thrones",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "2021-03-19",
            "Ended",
            "Honor the shield."
        )
    }

    fun generateRemoteMoviesTest(): List<ResponseMovie> {
        val movies = ArrayList<ResponseMovie>()
        movies.add(
            ResponseMovie(generateDataMovie())
        )
        return movies
    }

    fun generateDataMovie(): List<DataMovie> {
        val results = ArrayList<DataMovie>()
        for (i in 1..20) {
            results.add(
                DataMovie(
                    "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                    460465,
                    "Cruella",
                    "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg"
                )
            )
        }
        return results
    }

    fun generateRemoteTvshowTest(): List<ResponseTvshow> {
        val tvshow = ArrayList<ResponseTvshow>()
        tvshow.add(
            ResponseTvshow(generateDataTvshow())
        )
        return tvshow
    }

    fun generateDataTvshow(): List<DataTvshow> {
        val results = ArrayList<DataTvshow>()
        for (i in 1..20) {
            results.add(
                DataTvshow(
                    "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                    "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                    "Game of Thrones",
                    88396
                )
            )
        }
        return results
    }

    fun generateBookmarkListTest(): List<DetailEntity> {
        val bookmarks = ArrayList<DetailEntity>()
        for (i in 1..20) {
            bookmarks.add(
                DetailEntity(
                    88396,
                    "Game of Thrones",
                    "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                    "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                    "2021-03-19",
                    "Ended",
                    "Honor the shield."
                )
            )
        }
        return bookmarks
    }
}