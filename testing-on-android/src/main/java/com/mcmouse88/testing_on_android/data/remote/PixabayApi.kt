package com.mcmouse88.testing_on_android.data.remote

import com.mcmouse88.testing_on_android.BuildConfig
import com.mcmouse88.testing_on_android.data.remote.responses.ImageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<ImageDto>
}