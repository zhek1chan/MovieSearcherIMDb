package ru.kryu.dreamkinopoisk.ui.poster

import android.app.Activity
import android.os.Bundle
import ru.kryu.dreamkinopoisk.ui.util.Creator
import ru.kryu.dreamkinopoisk.R

class PosterActivity : Activity() {

    private val posterController = Creator.providePosterController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        posterController.onCreate()
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