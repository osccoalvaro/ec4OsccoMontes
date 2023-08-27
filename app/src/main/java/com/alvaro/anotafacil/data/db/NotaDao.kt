package com.alvaro.anotafacil.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alvaro.anotafacil.model.Note

@Dao
interface NotaDao {

    @Insert
    suspend fun addNoteFavorite(note: Note)

    @Query("SELECT * FROM nota4")
    suspend fun getFavorites() : List<Note>

    @Query("SELECT * FROM nota4 WHERE _id = :noteId")
    fun getNoteById(noteId: String): Note?

}