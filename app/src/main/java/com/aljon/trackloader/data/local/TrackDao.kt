package com.aljon.trackloader.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aljon.trackloader.data.entities.DatabaseTrack
import com.aljon.trackloader.data.entities.Track
import java.util.*

@Dao
interface TrackDao {

    @Query("SELECT * FROM DatabaseTrack")
    fun getTracks() : LiveData<List<Track>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTracks(vararg track: DatabaseTrack)

    @Query("SELECT * FROM DatabaseTrack WHERE trackId = :id")
    fun getTrack(id: Long): Track
}