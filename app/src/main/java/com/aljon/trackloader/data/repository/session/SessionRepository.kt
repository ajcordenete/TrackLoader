package com.aljon.trackloader.data.repository.session

import com.aljon.trackloader.data.entities.Session

/**
 * Interface class to be extended by all Session repository implementations.
 */
interface SessionRepository {
    /**
     * Save user session info
     *
     * @param lastVisited - date the user last loaded the app in string format
     * @param lastScreen - the last screen that the user viewed. @see Constants for string value
     * @param lastViewedTrack - id of the last viewed Track by the user
     */
    fun saveSession(lastVisited: String, lastScreen: String, lastViewedTrack: Long)

    /**
     * Get the user's session that represented its saved state
     *
     * @return the saved Session object
     */
    fun getSession(): Session
}