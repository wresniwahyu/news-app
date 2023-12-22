package com.wresni.news.data.source.remote.dto


import com.google.gson.annotations.SerializedName

data class SourceDto(
    @SerializedName("sources")
    val sources: List<SourceX>? = listOf(),
    @SerializedName("status")
    val status: String? = ""
)