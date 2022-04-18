package com.jamessaboia.prodmaisapp.Repository

import androidx.lifecycle.LiveData
import com.jamessaboia.prodmaisapp.Dao.NotesDao
import com.jamessaboia.prodmaisapp.Model.Notes

class NotesRepository(val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }

    fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int) {
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)
    }
}