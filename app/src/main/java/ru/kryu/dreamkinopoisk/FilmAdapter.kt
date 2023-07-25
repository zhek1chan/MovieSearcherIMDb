package ru.kryu.dreamkinopoisk

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilmAdapter(private val films: ArrayList<Film>,
                  private val onFilmClickListener: OnFilmClickListener
                  ): RecyclerView.Adapter<FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(parent)
    }

    override fun getItemCount(): Int = films.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(films[position])
    }

    fun interface OnFilmClickListener{
        fun onFilmClick(film: Film)
    }
}