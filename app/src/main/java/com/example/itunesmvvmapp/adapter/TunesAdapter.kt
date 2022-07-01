package com.example.itunesmvvmapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itunesmvvmapp.R
import com.example.itunesmvvmapp.model.TrackItem

class TunesAdapter : RecyclerView.Adapter<TunesViewHolder>(){

    private var musicList: MutableList<TrackItem> = mutableListOf()

    fun setTracksData(musicList: MutableList<TrackItem>){
        this.musicList.clear()
        this.musicList=musicList
        notifyDataSetChanged()
    }

    var onTrackClick: ((TrackItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TunesViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.song_layout,parent,false).apply {
            return  TunesViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: TunesViewHolder, position: Int) {
        val currentMusic = musicList[position]
        holder.setInformationToTheViewHolder(currentMusic)
        holder.rootView.setOnClickListener {
            onTrackClick?.invoke(currentMusic)
        }
    }

    override fun getItemCount(): Int = musicList.size

}

class TunesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    val rootView=itemView
    private val artistName: TextView = itemView.findViewById(R.id.artist_name)
    private val collectionName : TextView = itemView.findViewById(R.id.collection_name)
    private val trackPrice: TextView = itemView.findViewById(R.id.track_price)
    private val artworkUrl: ImageView = itemView.findViewById(R.id.artwork_url_60)

    fun setInformationToTheViewHolder(currentMusic: TrackItem) {
        artistName.text = "Artist : "+ currentMusic.artistName
        collectionName.text = "Collection : "+ currentMusic.collectionName
        trackPrice.text = "£."+currentMusic.trackPrice.toString()

        Glide.with(itemView)
            .load(currentMusic.artworkUrl60 )
            .centerCrop()
            .into(artworkUrl)
    }
}