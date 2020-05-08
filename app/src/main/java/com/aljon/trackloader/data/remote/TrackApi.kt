package com.aljon.trackloader.data.remote

import androidx.lifecycle.LiveData
import com.aljon.trackloader.utils.helper.ApiResponse
import com.aljon.trackloader.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackApi {

    @GET("search")
    fun getTracks(@Query("term") term: String = Constants.REST_PARAM_TERM_DEFAULT,
                  @Query("country") country: String = Constants.REST_PARAM_COUNTRY_DEFAULT,
                  @Query("media") media: String = Constants.REST_PARAM_MEDIA_DEFAULT): LiveData<ApiResponse<TrackApiResponse>>
}