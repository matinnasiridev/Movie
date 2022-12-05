package com.example.movie.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.DetailActivity
import com.example.movie.apiManager.ApiManager
import com.example.movie.apiManager.models.Data
import com.example.movie.apiManager.models.Movies
import com.example.movie.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), HomeAdapter.RecyclerCallBack {

    lateinit var binding: ActivityHomeBinding
    private val apiManager = ApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setList()
    }

    private fun setList() {
        var next = 1
        binding.btnNext.setOnClickListener {
            if (next == 1) {
                ins(next)
                next++
                Log.v("testApi", "next: $next")
            } else {
                ins(next++)
                Log.v("testApi", "next: $next")
            }
        }
        binding.btnLast.setOnClickListener {
            next -= 1
            ins(next)
            Log.v("testApi", "next: $next")
        }
    }

    private fun ins(page: Int) {
        apiManager.getMovies(object : ApiManager.ApiCallBack<Movies> {
            override fun number(): Int = page

            override fun onSuccess(data: Movies) {
                Log.v("testApi", data.metadata.current_page)
                binding.recyclerMain.adapter = HomeAdapter(data, this@HomeActivity)
                binding.recyclerMain.layoutManager = LinearLayoutManager(this@HomeActivity)
            }

            override fun onError(errorMessage: String) {
                Log.v("testApi", errorMessage)
            }

        })
    }

    override fun onCoinListener(data: Data) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("dataMovie", data)
        startActivity(intent)
    }
}