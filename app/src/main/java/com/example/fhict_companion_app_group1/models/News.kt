package com.example.fhict_companion_app_group1.models

import android.graphics.Bitmap

class News(
    val author: String,
    val title: String,
    val content: String,
    val image: String,
    private val pubDate: String,
    private var bitmap: Bitmap
) {
    fun setBitmap(bitmap: Bitmap) {
        this.bitmap = bitmap
    }

    fun getBitmap(): Bitmap {
        return  this.bitmap
    }

    fun getPublishedDate(): String {
        return pubDate.substring(0, 10)
    }

}