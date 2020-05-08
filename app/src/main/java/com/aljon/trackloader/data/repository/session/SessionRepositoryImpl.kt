package com.aljon.trackloader.data.repository.session

import android.content.SharedPreferences
import com.aljon.trackloader.data.entities.Session
import com.aljon.trackloader.utils.Constants
import javax.inject.Inject

/**
 * Repository that handles Session instances
 *
 * @param sharedPref - sharedPreference created for this app
 */
class SessionRepositoryImpl @Inject constructor(private val sharedPref: SharedPreferences):
    SessionRepository {

    override fun saveSession(lastVisited: String, lastScreen: String, lastViewedTrack: Long) {
        with(sharedPref.edit()) {
            putString(Constants.PREF_LAST_VISITED, lastVisited)
            putString(Constants.PREF_LAST_PAGE_SCREEN, lastScreen)
            putLong(Constants.PREF_LAST_VIEWED_TRACK, lastViewedTrack)
            apply()
        }
    }

    override fun getSession(): Session {
        return Session(
            sharedPref.getString(Constants.PREF_LAST_VISITED, "")!!,
            sharedPref.getString(Constants.PREF_LAST_PAGE_SCREEN, "")!!,
            sharedPref.getLong(Constants.PREF_LAST_VIEWED_TRACK, -1)
        )
    }
}