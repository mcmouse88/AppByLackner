package com.mcmouse88.work_manager_guide

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

interface FileApi {

    @GET("wp-content/uploads/2015/01/high-resolution-wallpapers-25.jpg")
    suspend fun downloadImage(): Response<ResponseBody>

    companion object {
        val instance: FileApi by lazy {
            Retrofit.Builder()
                .baseUrl("https://paulryan.com.au/")
                .build()
                .create()
        }
    }
}