package com.example.itunesmvvmapp.rest

import com.example.itunesmvvmapp.utils.TrackResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface TunesRepository {
    val tracksResponseFlow: StateFlow<TrackResponse>
    suspend fun getTracksList(trackMode:String)
}

class TunesRepositoryImpl(
    private val tunesApi: TunesApi
) : TunesRepository {

    private val _tracksResponseFlow: MutableStateFlow<TrackResponse> = MutableStateFlow(TrackResponse.LOADING)

    override val tracksResponseFlow: StateFlow<TrackResponse>
        get() = _tracksResponseFlow

    override suspend fun getTracksList(trackMode: String) {
        try {
            val response = tunesApi.retrieveTracks(trackMode)

            if (response.isSuccessful) {
                response.body()?.let {
                    _tracksResponseFlow.value = TrackResponse.SUCCESS(it)
                } ?: run {
                    _tracksResponseFlow.value =
                        TrackResponse.ERROR(IllegalStateException("Track details are coming as null!"))
                }
            } else {
                _tracksResponseFlow.value = TrackResponse.ERROR(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            _tracksResponseFlow.value = TrackResponse.ERROR(e)
        }
    }
}