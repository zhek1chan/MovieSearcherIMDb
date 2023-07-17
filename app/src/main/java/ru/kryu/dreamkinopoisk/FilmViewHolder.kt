package ru.kryu.dreamkinopoisk

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FilmViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.search_card, parent, false)
) {

    private var filmPoster: ImageView = itemView.findViewById(R.id.film_poster)
    private var filmName: TextView = itemView.findViewById(R.id.film_name)
    private var filmDescription: TextView = itemView.findViewById(R.id.film_description)

    fun bind(film: Film) {
        filmName.text = film.title
        filmDescription.text = film.description
        Glide.with(itemView)
            .load(film.image)
            .placeholder(R.drawable.image_placeholder)
            .into(filmPoster)
    }
}