package ru.kryu.dreamkinopoisk

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ImdbApiService {
    @GET("API/SearchMovie/{apiKey}/{expression}")
    fun getFilms(
        @Path("apiKey") apiKey: String,
        @Path("expression") expression: String
    ): Call<FilmsResponse>
}