package com.jcy.moviereviewapp.data.models


import com.google.gson.annotations.SerializedName

data class MovieItems(
    @SerializedName("display")
    val display: Int?,
    @SerializedName("items")
    val movieItems: List<MovieItem>?,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String?,
    @SerializedName("start")
    val start: Int?,
    @SerializedName("total")
    val total: Int?
)