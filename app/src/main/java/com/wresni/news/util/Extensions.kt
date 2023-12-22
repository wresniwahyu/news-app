package com.wresni.news.util

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun String.convertUtcToGmtPlus7(): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    val utcDate: Date
    try {
        utcDate = inputFormat.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }

    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    outputFormat.timeZone = TimeZone.getTimeZone("GMT+7")

    return outputFormat.format(utcDate)
}