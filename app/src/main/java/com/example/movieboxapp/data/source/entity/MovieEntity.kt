package com.example.movieboxapp.data.source.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieEntities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "imagePath")
    var imagePath: String?,
)
