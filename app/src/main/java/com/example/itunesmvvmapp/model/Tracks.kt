package com.example.itunesmvvmapp.model


import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: MutableList<TrackItem>
)