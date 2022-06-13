package com.example.itunesmvvmapp.rest
import com.example.itunesmvvmapp.model.Tracks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface TunesApi {

    //This will retrieve all the music details from Music

    @GET(SEARCH)
    suspend fun retrieveTracks(
        @Query("term")term:String,
        @Query("amp;media")media:String = MEDIA_TYPE,
        @Query("amp;entity")entity:String = ENTITY_TYPE,
        @Query("amp;limit")limit:String = LIMIT_50
    ): Response<Tracks>

    companion object{

        const val BASE_URL ="https://itunes.apple.com/"

        private const val SEARCH ="search"

        const val MEDIA_TYPE ="music"
        const val ENTITY_TYPE ="song"
        const val LIMIT_50 ="50"

        const val MUSIC_CLASSIC ="classic"
        const val MUSIC_ROCK ="rock"
        const val MUSIC_POP ="pop"

        const val TRACK_MODE_CLASSIC:Int =0
        const val TRACK_MODE_ROCK:Int =1
        const val TRACK_MODE_POP:Int =2
    }
}