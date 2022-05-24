package com.jamessaboia.prodmaisapp.ui.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamessaboia.prodmaisapp.Database.SecurityPrefences
import com.jamessaboia.prodmaisapp.Interface.Listeners.MainListener
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

    private lateinit var mMainListener: MainListener
    lateinit var binding: FragmentHomeBinding
    val viewModel: TaskViewModel by viewModels()
    var oldMyNotes = arrayListOf<Task>()
    lateinit var adapter: TaskAdapter
    private lateinit var mSecurityPrefences: SecurityPrefences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)

        binding.rcvAllNotes.layoutManager = LinearLayoutManager(requireContext())

        mSecurityPrefences = SecurityPrefences(requireContext())

        var size = mSecurityPrefences.getStoredString("SIZE")

        if(size == "" || size == null){
            mSecurityPrefences.storeString("SIZE", "-1")
            size = mSecurityPrefences.getStoredString("SIZE")
        }

        Login.idBoard?.let { viewModel.getTaks("${Login.token}", it) }!!.observe(viewLifecycleOwner, { taskList ->
            oldMyNotes = taskList as ArrayList<Task>
            adapter = TaskAdapter(requireContext(), taskList.sortedBy { it.createdAt })
            binding.rcvAllNotes.adapter = adapter

            if(oldMyNotes.size > 0 || oldMyNotes.size != size!!.toInt()){
                binding.progressBarHome.isVisible = false
            }
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
        val item3 = menu.findItem(R.id.menu_logout)

        val selecionarTodasView = item1.actionView
        val comentariosView = item3.actionView


        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            mMainListener = activity as MainListener
        } catch (e: ClassCastException) {
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_logout){
            mMainListener.goToLogin()
        } else if(item.itemId == R.id.menu_selecionar_todas){
            val taskList = oldMyNotes.sortedBy { it.createdAt }
            adapter = TaskAdapter(requireContext(), taskList)
            binding.rcvAllNotes.adapter = adapter
        } else if(item.itemId == R.id.menu_fazer){
            val taskList = oldMyNotes.filter { it.status  == "Do" }.sortedBy { it.createdAt }
            adapter = TaskAdapter(requireContext(), taskList)
            binding.rcvAllNotes.adapter = adapter
        } else if(item.itemId == R.id.menu_fazendo){
            val taskList = oldMyNotes.filter { it.status  == "Doing" }.sortedBy { it.createdAt }
            adapter = TaskAdapter(requireContext(), taskList)
            binding.rcvAllNotes.adapter = adapter
        } else if(item.itemId == R.id.menu_feito){
            val taskList = oldMyNotes.filter { it.status  == "Done" }.sortedBy { it.createdAt }
            adapter = TaskAdapter(requireContext(), taskList)
            binding.rcvAllNotes.adapter = adapter
        }

        return super.onOptionsItemSelected(item)
    }

}