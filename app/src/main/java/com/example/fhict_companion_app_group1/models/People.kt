package com.example.fhict_companion_app_group1.models

import android.graphics.Bitmap

class People (
    val id: String,
    val displayName: String,
    val mail: String,
    val office: String,
    val photo: String,
    val department: String,
    private var bitmap: Bitmap

)
{
    fun setBitmap(bitmap: Bitmap) {
        this.bitmap = bitmap
    }
    fun getBitmap(): Bitmap {
        return  this.bitmap
    }
}



