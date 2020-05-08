package com.aljon.trackloader.data

import com.aljon.trackloader.data.entities.DatabaseTrack
import com.aljon.trackloader.data.entities.NetworkTrack
import com.aljon.trackloader.data.entities.Track


/**
 * Data Mapper that converts network entity to database entity
 */
fun List<NetworkTrack>.asDatabaseEntity(): List<DatabaseTrack> {
    return map {
        DatabaseTrack(
            trackId = it.trackId,
            name = it.name,
            description = it.description,
            genre = it.genre,
            price = it.price,
            duration = it.durationMillis / 60000,
            artwork = it.artwork
        )
    }
}


/**
 * Data Mapper that converts database entity to domain entity
 */
fun List<DatabaseTrack>.asDomainEntity(): List<Track> {
    return map {
        Track(
            trackId = it.trackId,
            name = it.name,
            description = it.description,
            genre = it.genre,
            price = it.price,
            duration = it.duration,
            artwork = it.artwork
        )
    }
}