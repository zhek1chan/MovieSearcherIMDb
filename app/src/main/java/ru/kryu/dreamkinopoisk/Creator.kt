package ru.kryu.dreamkinopoisk

import ru.kryu.dreamkinopoisk.data.MoviesRepositoryImpl
import ru.kryu.dreamkinopoisk.data.network.RetrofitNetworkClient
import ru.kryu.dreamkinopoisk.domain.api.MoviesInteractor
import ru.kryu.dreamkinopoisk.domain.api.MoviesRepository
import ru.kryu.dreamkinopoisk.domain.impl.MoviesInteractorImpl

object Creator {
    private fun getMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }
}