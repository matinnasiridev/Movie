package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movie.apiManager.models.Data
import com.example.movie.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    private lateinit var dataFromHome: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataFromHome = intent.getParcelableExtra<Data>("dataMovie")!!
        hold()

    }
    private fun hold() {
        Glide
            .with(this)
            .load(dataFromHome.poster)
            .into(binding.imgPoster)

        binding.txtTitle.text = dataFromHome.title
        binding.txtYear.text = dataFromHome.year
        binding.txtCountry.text = dataFromHome.country

        Glide
            .with(this)
            .load(dataFromHome.images[0])
            .into(binding.imgTitle)
        Glide
            .with(this)
            .load(dataFromHome.images[1])
            .into(binding.imgTitle2)
        Glide
            .with(this)
            .load(dataFromHome.images[2])
            .into(binding.imgTitle3)

    }
}