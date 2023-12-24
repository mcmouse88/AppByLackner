package com.mcmouse88.stock_market_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company_listing")
data class CompanyListingEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val symbol: String,
    val exchange: String
)
