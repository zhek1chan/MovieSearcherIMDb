package ru.kryu.dreamkinopoisk.ui.poster

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.kryu.dreamkinopoisk.ui.util.Creator
import ru.kryu.dreamkinopoisk.R
import ru.kryu.dreamkinopoisk.presentation.poster.PosterPresenter
import ru.kryu.dreamkinopoisk.presentation.poster.PosterView

class PosterActivity : Activity(), PosterView {

    private lateinit var posterPresenter: PosterPresenter

    private lateinit var poster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Мы не можем создать PosterPresenter раньше,
        // потому что нам нужен imageUrl, который
        // станет доступен только после super.onCreate
        val imageUrl = intent.extras?.getString("poster", "") ?: ""
        posterPresenter = Creator.providePosterPresenter(this, imageUrl)

        setContentView(R.layout.activity_poster)
        poster = findViewById(R.id.poster)

        posterPresenter.onCreate()
    }

    override fun setupPosterImage(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }

}
/*class PosterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)

        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
            .load(intent.getStringExtra("poster"))
            .placeholder(R.drawable.image_placeholder)
            .into(imageView)
    }
}*/