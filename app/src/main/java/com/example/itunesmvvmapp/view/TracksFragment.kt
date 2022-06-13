package com.example.itunesmvvmapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunesmvvmapp.adapter.TunesAdapter
import com.example.itunesmvvmapp.databinding.FragmentTracksBinding
import com.example.itunesmvvmapp.rest.TunesApi
import com.example.itunesmvvmapp.utils.TrackResponse
import com.example.itunesmvvmapp.viewmodel.TunesViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "TracksFragment"
private const val ARG_TRACKMODE = "trackMode"
@AndroidEntryPoint
class TracksFragment : Fragment() {

    private lateinit var tunesAdapter: TunesAdapter
    private val viewModel: TunesViewModel by viewModels<TunesViewModel>()
    private val binding : FragmentTracksBinding by lazy{
        FragmentTracksBinding.inflate(layoutInflater)
    }

    private var trackmode: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trackmode = it.getInt(ARG_TRACKMODE,-1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tunesAdapter= TunesAdapter()
        binding.songRecycler.apply{
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = tunesAdapter
        }
        tunesAdapter.onTrackClick={trackItem ->
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.setDataAndType(Uri.parse(trackItem.previewUrl),"audio/*")
            Toast.makeText(requireContext(),"Now Playing: ${trackItem.trackName}", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
        when(trackmode){
             TunesApi.TRACK_MODE_CLASSIC -> {
                 viewModel.subscribeToMusicList(TunesApi.MUSIC_CLASSIC)
             }
            TunesApi.TRACK_MODE_ROCK -> {
                viewModel.subscribeToMusicList(TunesApi.MUSIC_ROCK)
            }
            TunesApi.TRACK_MODE_POP -> {
                viewModel.subscribeToMusicList(TunesApi.MUSIC_POP)
            }
        }
        viewModel.musicTrack.observe(viewLifecycleOwner) { trackState ->
            when (trackState) {
                is TrackResponse.LOADING -> {
                    binding.swipe.isRefreshing = true
                }
                is TrackResponse.SUCCESS -> {
                    binding.swipe.isRefreshing = false
                    tunesAdapter.setTracksData(trackState.tracks.results)
                }
                is TrackResponse.ERROR -> {
                    Toast.makeText(
                        requireContext(),
                        trackState.error.message,
                        Toast.LENGTH_LONG).show()
                    Log.d(TAG, "onCreateView: "+trackState.error.message)
                    binding.swipe.isRefreshing = false
                }
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(trackMode: Int) =
            TracksFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TRACKMODE, trackMode)
                }
            }
    }
}