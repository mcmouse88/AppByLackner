package com.mcmouse88.stock_market_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mcmouse88.stock_market_app.presentation.NavGraphs
import com.mcmouse88.stock_market_app.ui.theme.StockMarketTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}