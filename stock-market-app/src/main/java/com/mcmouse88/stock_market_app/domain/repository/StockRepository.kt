package com.mcmouse88.stock_market_app.domain.repository

import com.mcmouse88.stock_market_app.domain.model.CompanyInfo
import com.mcmouse88.stock_market_app.domain.model.CompanyListing
import com.mcmouse88.stock_market_app.domain.model.IntraDayInfo
import com.mcmouse88.stock_market_app.utils.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntraDayInfo(
        symbol: String
    ): Resource<List<IntraDayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>
}