package com.jamessaboia.prodmaisapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamessaboia.prodmaisapp.R
import com.jamessaboia.prodmaisapp.ViewModel.NotesViewModel
import com.jamessaboia.prodmaisapp.databinding.FragmentHomeBinding
import com.jamessaboia.prodmaisapp.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->

            binding.rcvAllNotes.layoutManager = LinearLayoutManager(requireContext())
            binding.rcvAllNotes.adapter= NotesAdapter(requireContext(), notesList)
        })


        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root
    }

}