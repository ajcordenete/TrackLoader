package com.aljon.trackloader.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aljon.trackloader.data.entities.DatabaseTrack

@Database(entities = [DatabaseTrack::class], version = 3)
abstract class TrackLoaderDatabase: RoomDatabase() {
    abstract fun trackDao(): TrackDao
}