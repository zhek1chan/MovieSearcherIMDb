package ru.kryu.dreamkinopoisk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class PosterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)

        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
            .load(intent.getStringExtra("poster"))
            .placeholder(R.drawable.image_placeholder)
            .into(imageView)
    }
}