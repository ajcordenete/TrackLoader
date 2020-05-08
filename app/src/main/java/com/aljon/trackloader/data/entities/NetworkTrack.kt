package com.aljon.trackloader.data.entities

import com.squareup.moshi.Json

/**
 * Network object equivalent of the Track object
 */
data class NetworkTrack (
    @Json(name = "trackId")
    val trackId: Long,

    @Json(name = "trackName")
    val name: String,

    @Json(name = "longDescription")
    val description: String,

    @Json(name = "primaryGenreName")
    val genre: String,

    @Json(name = "trackPrice")
    val price: Float,

    @Json(name = "artworkUrl100")
    val artwork: String,

    @Json(name = "trackTimeMillis")
    val durationMillis: Int,

    @Json(name = "previewUrl")
    val previewUrl: String
)