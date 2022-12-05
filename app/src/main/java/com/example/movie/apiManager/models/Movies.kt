package com.example.movie.apiManager.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val `data`: List<Data>,
    val metadata: Metadata
):Parcelable

@Parcelize
data class Metadata(
    val current_page: String,
    val page_count: Int,
    val per_page: Int,
    val total_count: Int
):Parcelable

@Parcelize
data class Data(
    val country: String,
    val genres: List<String>,
    val id: Int,
    val images: List<String>,
    val imdb_rating: String,
    val poster: String,
    val title: String,
    val year: String
):Parcelable