package ru.kryu.dreamkinopoisk.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.kryu.dreamkinopoisk.data.dto.MoviesSearchResponse


interface IMDbApiService {
    @GET("API/SearchMovie/{apiKey}/{expression}")
    fun searchMovies(
        @Path("apiKey") apiKey: String,
        @Path("expression") expression: String
    ): Call<MoviesSearchResponse>
}