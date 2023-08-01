package ru.kryu.dreamkinopoisk.ui.poster

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.kryu.dreamkinopoisk.R

class PosterActivity : Activity() {

    private lateinit var poster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        poster = findViewById(R.id.poster)
        val url = intent.extras?.getString("poster", "")

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