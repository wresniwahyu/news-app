package com.wresni.news.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("articles")
    val articles: List<Article>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = null
)