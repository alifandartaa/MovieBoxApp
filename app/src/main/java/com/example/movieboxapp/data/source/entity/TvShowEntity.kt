package com.example.movieboxapp.data.source.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvShowEntities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvshowId: Int?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "imagePath")
    var imagePath: String?,
)
