package com.jamessaboia.prodmaisapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jamessaboia.prodmaisapp.R
import com.jamessaboia.prodmaisapp.databinding.FragmentDrawerBinding


class DrawerFragment : Fragment() {
    private lateinit var binding: FragmentDrawerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drawer, container, false)
    }


}