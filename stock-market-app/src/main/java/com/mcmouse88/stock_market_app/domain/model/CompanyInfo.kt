package com.mcmouse88.stock_market_app.domain.model

data class CompanyInfo(
    val symbol: String,
    val description: String,
    val name: String,
    val country: String,
    val industry: String
)
