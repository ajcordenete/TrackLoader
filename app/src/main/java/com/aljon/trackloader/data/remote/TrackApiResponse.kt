package com.aljon.trackloader.data.remote

import com.aljon.trackloader.data.entities.NetworkTrack

/**
 * Data class used for parsing the json response from the Itunes Search API
 */
data class TrackApiResponse (
    val resultCount: Int,
    val results: List<NetworkTrack>
)