package com.jamessaboia.prodmaisapp.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jamessaboia.prodmaisapp.Model.Notes
import com.jamessaboia.prodmaisapp.Model.Task
import com.jamessaboia.prodmaisapp.R
import com.jamessaboia.prodmaisapp.databinding.ItemNotesBinding
import com.jamessaboia.prodmaisapp.ui.Fragments.HomeFragmentDirections

class TaskAdapter(val requireContext: Context, val notesList: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.notesViewHolder>() {


    class notesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitle.text = data.name
        holder.binding.notesDescricao.text = data.description

//        when (data.priority) {
//            "1" -> {
//                holder.binding.viewPriority.setBackgroundResource(R.drawable.circulo_verde)
//            }
//            "2" -> {
//                holder.binding.viewPriority.setBackgroundResource(R.drawable.circulo_amarelo)
//            }
//            "3" -> {
//                holder.binding.viewPriority.setBackgroundResource(R.drawable.circulo_vermelho)
//            }
//        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)

        }

    }

    override fun getItemCount() = notesList.size
}