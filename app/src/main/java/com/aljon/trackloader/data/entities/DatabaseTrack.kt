package com.aljon.trackloader.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Database entity to be used for offline cache mechanism
 */
@Entity
data class DatabaseTrack(

    @PrimaryKey
    @ColumnInfo(name = "trackId") val trackId: Long,

    @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "description") val description: String,

    @ColumnInfo(name = "genre") val genre: String,

    @ColumnInfo(name = "price") val price: Float,

    @ColumnInfo(name = "duration") val duration: Int,

    @ColumnInfo(name = "artwork") val artwork: String
)
