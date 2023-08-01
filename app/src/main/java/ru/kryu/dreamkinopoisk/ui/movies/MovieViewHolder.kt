package ru.kryu.dreamkinopoisk.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.kryu.dreamkinopoisk.R
import ru.kryu.dreamkinopoisk.domain.models.Movie

class MovieViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.list_item_movie, parent, false)) {

    var cover: ImageView = itemView.findViewById(R.id.film_poster)
    var title: TextView = itemView.findViewById(R.id.film_name)
    var description: TextView = itemView.findViewById(R.id.film_description)

    fun bind(movie: Movie) {
        Glide.with(itemView)
            .load(movie.image)
            .into(cover)

        title.text = movie.title
        description.text = movie.description
    }
}
/*class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.search_card, parent, false)
) {

    private var filmPoster: ImageView = itemView.findViewById(R.id.film_poster)
    private var filmName: TextView = itemView.findViewById(R.id.film_name)
    private var filmDescription: TextView = itemView.findViewById(R.id.film_description)

    fun bind(film: Movie) {
        filmName.text = film.title
        filmDescription.text = film.description
        Glide.with(itemView)
            .load(film.image)
            .placeholder(R.drawable.image_placeholder)
            .into(filmPoster)
    }
}*/