package ru.kryu.dreamkinopoisk.presentation.movies

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kryu.dreamkinopoisk.ui.util.Creator
import ru.kryu.dreamkinopoisk.R
import ru.kryu.dreamkinopoisk.domain.api.MoviesInteractor
import ru.kryu.dreamkinopoisk.domain.models.Movie
import ru.kryu.dreamkinopoisk.ui.movies.MoviesAdapter

class MoviesSearchPresenter(
    private val view: MoviesView,
    private val context: Context
) {
    private val movies = ArrayList<Movie>()
    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private val handler = Handler(Looper.getMainLooper())
    private var lastSearchText: String? = null

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }

    fun searchDebounce(changedText: String) {
        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            view.showPlaceholderMessage(false)
            view.showMoviesList(false)
            view.showProgressBar(true)

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        view.showProgressBar(false)
                        if (foundMovies != null) {

                            // Обновляем список на экране
                            movies.clear()
                            movies.addAll(foundMovies)
                            view.updateMoviesList(movies)
                            view.showMoviesList(true)
                        }
                        if (errorMessage != null) {
                            showMessage(context.getString(R.string.something_went_wrong), errorMessage)
                        } else if (movies.isEmpty()) {
                            showMessage(context.getString(R.string.nothing_found), "")
                        } else {
                            hideMessage()
                        }
                    }
                }
            })
        }
    }

    private fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            view.showPlaceholderMessage(true)

            movies.clear()
            view.updateMoviesList(movies)

            view.changePlaceholderText(text)
            if (additionalMessage.isNotEmpty()) {
                // Добавили метод показа Toast
                view.showMessage(additionalMessage)
            }
        } else {
            view.showPlaceholderMessage(false)
        }
    }

    private fun hideMessage() {
        // Заменили работу с элементами UI на
        // вызовы методов интерфейса
        view.showPlaceholderMessage(false)
    }
    fun onDestroy() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }
    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }
}