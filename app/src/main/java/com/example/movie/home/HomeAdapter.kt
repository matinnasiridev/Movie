package com.example.movie.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.apiManager.models.Data
import com.example.movie.apiManager.models.Movies
import com.example.movie.databinding.ItemMovieBinding


class HomeAdapter(
    private val data: Movies,
    private val recyclerCallBack: RecyclerCallBack
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    lateinit var binding: ItemMovieBinding

    inner class HomeViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        fun bindViews(data: Data) {

            binding.textTitle.text = data.title
            binding.txtYear.text = data.year
            binding.txtCountry.text = data.country

            Glide
                .with(binding.root)
                .load(data.poster)
                .into(binding.imgPoster)

            itemView.setOnClickListener {
                recyclerCallBack.onCoinListener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindViews(data.data[position])
    }

    override fun getItemCount(): Int = data.metadata.per_page

    interface RecyclerCallBack {
        fun onCoinListener(data: Data)
    }
}