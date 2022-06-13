package com.example.itunesmvvmapp.utils

import com.example.itunesmvvmapp.model.Tracks

sealed class TrackResponse{
    object LOADING : TrackResponse()
    class SUCCESS(val tracks: Tracks): TrackResponse()
    class ERROR(val error: Throwable): TrackResponse()
}
