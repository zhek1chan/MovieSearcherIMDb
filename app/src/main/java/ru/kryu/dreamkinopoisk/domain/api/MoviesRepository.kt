package ru.kryu.dreamkinopoisk.domain.api

import ru.kryu.dreamkinopoisk.domain.models.Movie
import ru.kryu.dreamkinopoisk.ui.util.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
}