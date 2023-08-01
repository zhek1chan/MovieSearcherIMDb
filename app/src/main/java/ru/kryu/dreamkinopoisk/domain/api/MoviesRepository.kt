package ru.kryu.dreamkinopoisk.domain.api

import ru.kryu.dreamkinopoisk.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): List<Movie>
}