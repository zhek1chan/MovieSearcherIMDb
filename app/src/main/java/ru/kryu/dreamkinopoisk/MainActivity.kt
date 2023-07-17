package ru.kryu.dreamkinopoisk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    //необходимо создать ресурс api_key
    private val apiKey: String by lazy { resources.getString(R.string.api_key) }
    private val baseUrl = "https://imdb-api.com"

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val retrofitApiService = retrofit.create(ImdbApiService::class.java)

    private val films = ArrayList<Film>()
    private val adapter = FilmAdapter(films)//{
//        val intent = Intent(this, PosterActivity::class.java)
//        intent.putExtra("poster", it.image)
//        startActivity(intent)
//    }

    private val searchInput: EditText by lazy { findViewById(R.id.search_input) }
    private val searchButton: Button by lazy { findViewById(R.id.search_button) }
    private val searchRecycler: RecyclerView by lazy { findViewById(R.id.search_recycler) }
    private val textFail: TextView by lazy { findViewById(R.id.text_fail) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        searchRecycler.adapter = adapter

        searchButton.setOnClickListener {
            if (searchInput.text.isNotEmpty()) {
                showFilms()
            }
        }
    }

    private fun showFilms() {
        val expression = searchInput.text.toString()
        searchInput.setText("")
        searchInput.clearFocus()
        films.clear()
        textFail.text = ""
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(window.decorView.windowToken, 0)
        textFail.visibility = TextView.GONE
        retrofitApiService.getFilms(apiKey, expression).enqueue(object : Callback<FilmsResponse> {
            override fun onResponse(
                call: Call<FilmsResponse>,
                response: Response<FilmsResponse>
            ) {
                if (response.code() == 200) {
                    films.addAll(response.body()?.results!!)
                    adapter.notifyDataSetChanged()
                    if (films.isEmpty()){
                        textFail.visibility = TextView.VISIBLE
                        textFail.setText("Ничего не найдено")
                    }
                } else {
                    textFail.visibility = TextView.VISIBLE
                    textFail.setText("Что-то пошло не так")
                }
            }

            override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {
                textFail.visibility = TextView.VISIBLE
                textFail.setText("Ошибка")
            }

        })

    }


}