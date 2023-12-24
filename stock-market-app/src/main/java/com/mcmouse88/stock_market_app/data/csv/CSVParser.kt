package com.mcmouse88.stock_market_app.data.csv

import java.io.InputStream

interface CSVParser<out T> {
    suspend fun parse(stream: InputStream): List<T>
}