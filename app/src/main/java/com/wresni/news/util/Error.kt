package com.wresni.news.util

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("message")
    val message: String? = null,
)