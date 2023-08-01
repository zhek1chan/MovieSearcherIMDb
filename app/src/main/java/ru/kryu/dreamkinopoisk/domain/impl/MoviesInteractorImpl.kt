package ru.kryu.dreamkinopoisk.domain.impl

import ru.kryu.dreamkinopoisk.domain.api.MoviesInteractor
import ru.kryu.dreamkinopoisk.domain.api.MoviesRepository
import ru.kryu.dreamkinopoisk.ui.util.Resource
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        executor.execute {
            when(val resource = repository.searchMovies(expression)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }
        }
    }
}