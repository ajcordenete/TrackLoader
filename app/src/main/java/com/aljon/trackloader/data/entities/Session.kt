package com.aljon.trackloader.data.entities

/**
 * Data class for storing user session
 */
data class Session(
    val lastVisited: String,
    val lastPageScreen: String,
    val lastViewedTrack: Long
)