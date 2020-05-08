package com.aljon.trackloader.data.entities

import android.os.Parcelable
import com.aljon.trackloader.utils.Constants
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Domain object class that will be use by the UI across the whole app
 */
data class Track(
    val trackId: Long,
    val name: String,
    val description: String,
    val genre: String,
    val price: Float,
    val duration: Int,
    val artwork: String
)