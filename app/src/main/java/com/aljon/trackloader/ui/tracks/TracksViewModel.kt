package com.aljon.trackloader.ui.tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aljon.trackloader.data.repository.session.SessionRepository
import com.aljon.trackloader.data.repository.track.TrackRepository
import com.aljon.trackloader.utils.Constants
import com.aljon.trackloader.utils.Event
import com.aljon.trackloader.utils.toTimeElapsed
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class TracksViewModel @Inject constructor(
    private val trackRepository: TrackRepository,
    private val sessionRepository: SessionRepository
): ViewModel() {

    private val _refreshItems = MutableLiveData<Unit>()

    val tracks = Transformations.switchMap(_refreshItems) {
        trackRepository.getTracks()
    }

    //boolean binding to check if tracks is empty
    val emptyTracks = Transformations.map(tracks) {
        it.data?.isEmpty()
    }

    //event to indicate that user session's last screen is TrackDetail
    private val _sessionTrackDetailResume = MutableLiveData<Event<Long>>()
    val sessionTrackDetailResume: LiveData<Event<Long>> get() = _sessionTrackDetailResume

    private val _lastVisited = MutableLiveData<String>()
    val lastVisited: LiveData<String> get() = _lastVisited

    init {
        _refreshItems.value = Unit

        //called here in init so that session will only be checked once
        getSession()
    }

    private fun getSession() {
        val session = sessionRepository.getSession()

        if(session.lastPageScreen.contentEquals(Constants.PREF_SCREEN_TRACK_DETAIL)) {
            resumeTrackDetail(session.lastViewedTrack)
        }

        if(session.lastVisited.isNotEmpty()) {
            _lastVisited.value = session.lastVisited
        }
    }

    fun saveSession() {
        sessionRepository.saveSession(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.PREF_DATE_TIME_PATTERN)),
            Constants.PREF_SCREEN_TRACKS,
            -1
        )
    }

    private fun resumeTrackDetail(trackID: Long) {
        _sessionTrackDetailResume.value = Event(trackID)
    }
}