package com.mcmouse88.koin_guide

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val appModule = module {

    single<MyApi> {
        Retrofit.Builder()
            .baseUrl("https://google.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    single<MainRepository> {
        MainRepositoryImpl(api = get())
    }

    viewModel {
        MainViewModel(repository = get())
    }
}