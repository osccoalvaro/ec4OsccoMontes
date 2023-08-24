package com.alvaro.anotafacil.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alvaro.anotafacil.data.db.NotaDataBase
import com.alvaro.anotafacil.data.repository.NoteRepository
import com.alvaro.anotafacil.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository
    private var _favorites: MutableLiveData<List<Note>> = MutableLiveData()
    var favorites: LiveData<List<Note>> = _favorites

    init {
        val db = NotaDataBase.getDataBase(application)
        repository = NoteRepository(db)
    }

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getFavorites()
            _favorites.postValue(data)
        }
    }

}