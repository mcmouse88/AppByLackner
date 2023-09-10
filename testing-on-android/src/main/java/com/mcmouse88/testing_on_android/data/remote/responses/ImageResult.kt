package com.mcmouse88.testing_on_android.data.remote.responses

import com.google.gson.annotations.SerializedName

data class ImageResult(
    val comments: Int,
    val downloads: Int,
    val favorites: Int,
    val id: Int,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val largeImageUrl: String,
    val likes: Int,
    val pageUrl: String,
    val previewWidth: Int,
    val previewHeight: Int,
    val previewUrl: String,
    val tags: String,
    val type: String,
    val user: String,
    @SerializedName("user_id") val userId: Int,
    val views: Int,
    val webFormatWidth: Int,
    val webFormatHeight: Int,
    val webFormatUrl: String,
)
