package com.jamessaboia.prodmaisapp.ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamessaboia.prodmaisapp.Model.Login
import com.jamessaboia.prodmaisapp.Model.Notes
import com.jamessaboia.prodmaisapp.Model.Task
import com.jamessaboia.prodmaisapp.R
import com.jamessaboia.prodmaisapp.ViewModel.NotesViewModel
import com.jamessaboia.prodmaisapp.ViewModel.TaskViewModel
import com.jamessaboia.prodmaisapp.databinding.FragmentHomeBinding
import com.jamessaboia.prodmaisapp.ui.Adapter.NotesAdapter
import com.jamessaboia.prodmaisapp.ui.Adapter.TaskAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: TaskViewModel by viewModels()
    var oldMyNotes = arrayListOf<Task>()
    lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Login.token?.let { Log.v("TOKEN : ", it) }
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)

        binding.rcvAllNotes.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getTaks("${Login.token}")!!.observe(viewLifecycleOwner, { taskList ->
            oldMyNotes = taskList as ArrayList<Task>
            adapter = TaskAdapter(requireContext(), taskList)
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