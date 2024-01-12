package com.mcmouse88.koin_guide

class MainRepositoryImpl(
    private val api: MyApi
) : MainRepository {

    override fun doNetworkCall() {
        api.callApi()
    }
}