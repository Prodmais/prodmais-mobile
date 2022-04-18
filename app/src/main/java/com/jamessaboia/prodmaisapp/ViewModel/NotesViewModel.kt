package com.jamessaboia.prodmaisapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.jamessaboia.prodmaisapp.Database.NotesDatabase
import com.jamessaboia.prodmaisapp.Model.Notes
import com.jamessaboia.prodmaisapp.Repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    var repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNotes(id:Int) {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }

}
