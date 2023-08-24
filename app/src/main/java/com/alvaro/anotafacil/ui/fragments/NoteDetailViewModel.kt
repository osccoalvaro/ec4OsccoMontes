package com.alvaro.anotafacil.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alvaro.anotafacil.data.db.NotaDataBase
import com.alvaro.anotafacil.data.repository.NoteRepository
import com.alvaro.anotafacil.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteDetailViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NoteRepository
    init {
        val db = NotaDataBase.getDataBase(application)
        repository = NoteRepository(db)
    }

    fun addFavorites(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNoteFavorites(note)
        }
    }

    // Método para verificar si una nota ya es un favorito
    suspend fun isFavorite(noteId: String): Boolean {
        return repository.isNoteFavorite(noteId)
    }

}