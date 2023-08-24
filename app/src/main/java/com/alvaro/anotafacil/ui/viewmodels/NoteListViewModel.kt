package com.alvaro.anotafacil.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaro.anotafacil.data.repository.NoteRepository
import com.alvaro.anotafacil.data.repository.NoteServiceResult
import com.alvaro.anotafacil.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteListViewModel: ViewModel() {

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    val repository = NoteRepository()

    fun getNotesFromService() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getNotes()
            when(response) {
                is NoteServiceResult.Success -> {
                    _notes.postValue(response.data.docs)
                }
                is NoteServiceResult.Error -> {
                    //
                }
            }
        }
    }

}