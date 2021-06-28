package com.example.movieboxapp.data.source.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.data.source.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class, DetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieTvDatabase : RoomDatabase() {
    abstract fun movieTvDao(): MovieTvDao

    companion object {
        @Volatile
        private var INSTANCE: MovieTvDatabase? = null

        fun getInstance(context: Context): MovieTvDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieTvDatabase::class.java,
                    "MovieTv.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}