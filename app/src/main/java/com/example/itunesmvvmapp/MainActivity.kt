package com.example.itunesmvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.itunesmvvmapp.adapter.FragmentsAdapter
import com.example.itunesmvvmapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.musicAlbumContainer.adapter = FragmentsAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.menuBar, binding.musicAlbumContainer) { tab, position ->
            when(position){
                0 -> {
                    tab.text = "Classic"
                }
                1 -> {
                    tab.text = "Pop"
                }
                else -> {
                    tab.text = "Rock"
                }
            }
        }.attach()
    }
}