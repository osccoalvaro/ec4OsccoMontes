package com.alvaro.anotafacil.data.repository

import com.alvaro.anotafacil.data.db.NotaDao
import com.alvaro.anotafacil.data.db.NotaDataBase
import com.alvaro.anotafacil.data.response.ListNoteResponse
import com.alvaro.anotafacil.model.Note
import com.alvaro.anotafacil.data.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(val db: NotaDataBase? = null) {

    private val dao: NotaDao? = db?.notaDao()

    suspend fun getNotes(): NoteServiceResult<ListNoteResponse> {
        return try {
            val response = RetrofitHelper.noteService.getAllNotes()
            NoteServiceResult.Success(response)
        } catch (e: java.lang.Exception) {
            NoteServiceResult.Error(e)
        }
    }

    suspend fun getFavorites() : List<Note> {
        dao?.let {
            return dao.getFavorites()
        } ?: kotlin.run {
            return listOf()
        }
    }

    suspend fun addNoteFavorites(note: Note) {
        dao?.let {
            dao.addNoteFavorite(note)
        }
    }

    //add
    suspend fun isNoteFavorite(noteId: String): Boolean {
        return withContext(Dispatchers.IO) {
            dao?.getNoteById(noteId) != null
        }
    }



}