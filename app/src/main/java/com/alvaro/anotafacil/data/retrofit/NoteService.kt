package com.alvaro.anotafacil.data.retrofit

import com.alvaro.anotafacil.data.response.ListNoteResponse
import retrofit2.http.GET

interface NoteService {

    @GET("personajes?limit=20")
    suspend fun getAllNotes(): ListNoteResponse

}