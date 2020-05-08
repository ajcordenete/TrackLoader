package com.aljon.trackloader.data.repository.track

import androidx.lifecycle.LiveData
import com.aljon.trackloader.data.entities.Track
import com.aljon.trackloader.data.asDatabaseEntity
import com.aljon.trackloader.data.local.TrackDao
import com.aljon.trackloader.data.remote.TrackApi
import com.aljon.trackloader.data.remote.TrackApiResponse
import com.aljon.trackloader.utils.helper.ApiResponse
import com.aljon.trackloader.utils.helper.AppExecutors
import com.aljon.trackloader.utils.helper.NetworkBoundResource
import com.aljon.trackloader.utils.helper.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repository that handles Track instances
 *
 * @param trackApi - retrofit REST interface
 * @param trackDao - Dao class for DatabaseTrack
 * @param appExecutors - executor pool
 * @param ioDispatcher - Coroutine context
 */
class TrackRepositoryImpl @Inject constructor(private val trackApi: TrackApi,
                                              private val trackDao: TrackDao,
                                              private val appExecutors: AppExecutors,
                                              private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):
    TrackRepository {

    override fun getTracks(): LiveData<Resource<List<Track>>> {
        return object : NetworkBoundResource<List<Track>, TrackApiResponse>(appExecutors) {

            override fun shouldFetch(data: List<Track>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Track>> {
                return trackDao.getTracks()
            }

            override fun createCall(): LiveData<ApiResponse<TrackApiResponse>> {
                return trackApi.getTracks()
            }

            override fun saveCallResult(item: TrackApiResponse) {
                trackDao.insertTracks(*item.results.asDatabaseEntity().toTypedArray())
            }
        }.asLiveData()
    }

    override suspend fun getTrack(id: Long): Track = withContext(ioDispatcher) {
        return@withContext trackDao.getTrack(id)
    }
}