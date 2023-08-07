package ru.kryu.dreamkinopoisk.ui.util

import android.app.Activity
import android.content.Context
import ru.kryu.dreamkinopoisk.data.MoviesRepositoryImpl
import ru.kryu.dreamkinopoisk.data.network.RetrofitNetworkClient
import ru.kryu.dreamkinopoisk.domain.api.MoviesInteractor
import ru.kryu.dreamkinopoisk.domain.api.MoviesRepository
import ru.kryu.dreamkinopoisk.domain.impl.MoviesInteractorImpl
import ru.kryu.dreamkinopoisk.presentation.movies.MoviesSearchPresenter
import ru.kryu.dreamkinopoisk.presentation.PosterController
import ru.kryu.dreamkinopoisk.presentation.movies.MoviesView
import ru.kryu.dreamkinopoisk.presentation.poster.PosterPresenter
import ru.kryu.dreamkinopoisk.presentation.poster.PosterView
import ru.kryu.dreamkinopoisk.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }
    fun provideMoviesSearchPresenter(
        moviesView: MoviesView,
        context: Context,
    ): MoviesSearchPresenter {
        return MoviesSearchPresenter(
            view = moviesView,
            context = context,
        )
    }
    fun providePosterPresenter(
        posterView: PosterView,
        imageUrl: String
    ): PosterPresenter {
        return PosterPresenter(posterView, imageUrl)
    }
}