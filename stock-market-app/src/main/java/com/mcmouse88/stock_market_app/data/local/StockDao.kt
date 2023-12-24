package com.mcmouse88.stock_market_app.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface StockDao {

    @Upsert
    suspend fun insertCompanyListing(
        companyListingEntities: List<CompanyListingEntity>
    )

    @Query("DELETE FROM company_listing")
    suspend fun clearCompanyListing()

    @Query("""
        SELECT * FROM company_listing
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%'
        OR UPPER(:query) == symbol
    """)
    suspend fun searchCompanyListing(
        query: String
    ): List<CompanyListingEntity>
}