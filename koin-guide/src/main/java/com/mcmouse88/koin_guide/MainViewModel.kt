package com.mcmouse88.koin_guide

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    fun doNetWorkCall(string: String) {
        // repository.doNetworkCall()
        Log.e(MainViewModel::class.java.simpleName, "doNetWorkCall: $string")
    }
}