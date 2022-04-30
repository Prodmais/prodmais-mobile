package com.jamessaboia.prodmaisapp.ui.Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamessaboia.prodmaisapp.Model.Notes
import com.jamessaboia.prodmaisapp.R
import com.jamessaboia.prodmaisapp.ViewModel.NotesViewModel
import com.jamessaboia.prodmaisapp.databinding.FragmentHomeBinding
import com.jamessaboia.prodmaisapp.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)

        binding.rcvAllNotes.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter
        })


        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_superior, menu)

        val item1 = menu.findItem(R.id.menu_selecionar_todas)
        val item2 = menu.findItem(R.id.menu_excluir)
        val item3 = menu.findItem(R.id.menu_comentarios)

        val selecionarTodasView = item1.actionView
        val excluirView = item2.actionView
        val comentariosView = item3.actionView


        super.onCreateOptionsMenu(menu, inflater)
    }

}