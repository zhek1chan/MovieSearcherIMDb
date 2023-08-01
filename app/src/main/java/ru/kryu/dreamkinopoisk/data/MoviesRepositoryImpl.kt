package ru.kryu.dreamkinopoisk.data

import ru.kryu.dreamkinopoisk.data.dto.MoviesSearchRequest
import ru.kryu.dreamkinopoisk.data.dto.MoviesSearchResponse
import ru.kryu.dreamkinopoisk.domain.api.MoviesRepository
import ru.kryu.dreamkinopoisk.domain.models.Movie
import ru.kryu.dreamkinopoisk.ui.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}