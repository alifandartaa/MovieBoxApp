package com.example.movieboxapp.data.source.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detailEntities")
data class DetailEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "detailId")
        var id: Int,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "description")
        var description: String,

        @ColumnInfo(name = "imagePath")
        var imagePath: String,

        @ColumnInfo(name = "releaseDate")
        var releaseDate: String,

        @ColumnInfo(name = "status")
        var status: String,

        @ColumnInfo(name = "tagLine")
        var tagline: String,

        @ColumnInfo(name = "bookmarked")
        var bookmarked: Boolean = false
)
