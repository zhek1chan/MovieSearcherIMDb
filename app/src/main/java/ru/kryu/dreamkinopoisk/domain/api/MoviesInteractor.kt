package ru.kryu.dreamkinopoisk.domain.api

import ru.kryu.dreamkinopoisk.domain.models.Movie

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }
}