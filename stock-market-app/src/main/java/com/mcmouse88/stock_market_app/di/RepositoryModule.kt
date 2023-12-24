package com.mcmouse88.stock_market_app.di

import com.mcmouse88.stock_market_app.data.csv.CSVParser
import com.mcmouse88.stock_market_app.data.csv.CompanyListingsParser
import com.mcmouse88.stock_market_app.data.csv.IntraDayInfoParser
import com.mcmouse88.stock_market_app.data.repository.StockRepositoryImpl
import com.mcmouse88.stock_market_app.domain.model.CompanyListing
import com.mcmouse88.stock_market_app.domain.model.IntraDayInfo
import com.mcmouse88.stock_market_app.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface RepositoryModule {

    @[Binds Singleton]
    fun bindCompanyListingsParser(impl: CompanyListingsParser): CSVParser<CompanyListing>

    @[Binds Singleton]
    fun bindStockRepository(impl: StockRepositoryImpl): StockRepository

    @[Binds Singleton]
    fun bindIntraDayParser(impl: IntraDayInfoParser): CSVParser<IntraDayInfo>
}