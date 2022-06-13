package com.example.itunesmvvmapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.itunesmvvmapp.view.TracksFragment

class FragmentsAdapter(
    private val fragmentManager: FragmentManager,
    private val lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle)
{
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return  TracksFragment.newInstance(position)
    }
}