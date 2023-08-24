package com.alvaro.anotafacil.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl("https://apisimpsons.fly.dev/api/").addConverterFactory(GsonConverterFactory.create()).build()
    val noteService: NoteService = retrofit.create(NoteService::class.java)

}