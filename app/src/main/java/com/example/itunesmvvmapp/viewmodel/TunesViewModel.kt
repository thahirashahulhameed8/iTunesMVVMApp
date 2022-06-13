package com.example.itunesmvvmapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesmvvmapp.rest.TunesRepository
import com.example.itunesmvvmapp.utils.TrackResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class TunesViewModel @Inject constructor(
    private val tunesRepository: TunesRepository
): ViewModel() {
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob()+ioDispatcher)
    private val _musicTrackData : MutableLiveData<TrackResponse> = MutableLiveData(TrackResponse.LOADING)

    val musicTrack: LiveData<TrackResponse> get() = _musicTrackData

    fun subscribeToMusicList(trackMode:String) {
        _musicTrackData.postValue(TrackResponse.LOADING)
        collectMusicList()
        coroutineScope.launch(ioDispatcher){
            tunesRepository.getTracksList(trackMode)
        }
    }

    private fun collectMusicList() {
        coroutineScope.launch(ioDispatcher) {
            tunesRepository.tracksResponseFlow.collect { trackState ->
                _musicTrackData.postValue(trackState)
            }
        }
    }
}