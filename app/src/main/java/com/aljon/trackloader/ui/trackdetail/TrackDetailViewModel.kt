package com.aljon.trackloader.ui.trackdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aljon.trackloader.data.entities.Track
import com.aljon.trackloader.data.repository.session.SessionRepository
import com.aljon.trackloader.data.repository.track.TrackRepository
import com.aljon.trackloader.utils.Constants
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class TrackDetailViewModel @Inject constructor(private val trackRepository: TrackRepository,
                                               private val sessionRepository: SessionRepository
): ViewModel() {

    private val _track = MutableLiveData<Track>()
    val track: LiveData<Track> get() = _track

    fun loadTrackDetail(trackID: Long) = viewModelScope.launch {
        _track.value = trackRepository.getTrack(trackID)

        //update the session every time a track is loaded
        saveSession(trackID)
    }

    private fun saveSession(trackId: Long) {
        sessionRepository.saveSession(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.PREF_DATE_TIME_PATTERN)),
            Constants.PREF_SCREEN_TRACK_DETAIL,
            trackId
        )
    }
}