package com.jamessaboia.prodmaisapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.jamessaboia.prodmaisapp.Model.Notes
import com.jamessaboia.prodmaisapp.R
import com.jamessaboia.prodmaisapp.ViewModel.NotesViewModel
import com.jamessaboia.prodmaisapp.databinding.FragmentCreateNotesBinding

class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
//  var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

//        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
//
//        binding.pGreen.setOnClickListener {
//            priority = "1"
//            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
//            binding.pYellow.setImageResource(0)
//            binding.pRed.setImageResource(0)
//        }
//        binding.pYellow.setOnClickListener {
//            priority = "2"
//            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
//            binding.pGreen.setImageResource(0)
//            binding.pRed.setImageResource(0)
//        }
//        binding.pRed.setOnClickListener {
//            priority = "3"
//            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
//            binding.pGreen.setImageResource(0)
//            binding.pYellow.setImageResource(0)
//        }


        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }


        return binding.root
    }

    private fun createNotes(it: View?) {

        val title = binding.edtTitle.text.toString()
        val notes = binding.edtNotes.text.toString()

        if (title.isEmpty() || title.isBlank()){
            Toast.makeText(requireContext(), "Adicione um título a tarefa!", Toast.LENGTH_SHORT).show()
        } else {
            val data = Notes(
                null,
                title = title,
                notes = notes
            ) //adicionar " priority " dentro do paramentro se quiseres por de volta esta feature
            viewModel.addNotes(data)

            Toast.makeText(requireContext(), "Tarefa Criada com Sucesso!", Toast.LENGTH_SHORT).show()

            Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
        }
    }



}