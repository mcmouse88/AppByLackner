package com.mcmouse88.testing_on_android.data.remote.responses

data class ImageDto(
    val hits: List<ImageResult>,
    val total: Int,
    val totalHits: Int
)