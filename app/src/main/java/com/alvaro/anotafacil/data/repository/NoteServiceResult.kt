package com.alvaro.anotafacil.data.repository

sealed class NoteServiceResult<T>(data: T? =null, error: java.lang.Exception? =null) {

    data class Success<T>(val data: T): NoteServiceResult<T>(null)
    data class Error<T>(val error: Exception): NoteServiceResult<T>(null, error)

}