package com.aljon.trackloader.data.repository.track

import androidx.lifecycle.LiveData
import com.aljon.trackloader.data.entities.Track
import com.aljon.trackloader.utils.helper.Resource

/**
 * Interface class to be extended by all Track repository implementations.
 */
interface TrackRepository {

    /**
     * Loads all track available from source
     *
     * @return List of tracks available wrapped in a LiveData and Resource for status handling
     */
    fun getTracks(): LiveData<Resource<List<Track>>>

    /**
     * Get specific track by id
     * @param id - unique trackId identifier
     *
     * @return Track object
     */
    suspend fun getTrack(id: Long): Track
}