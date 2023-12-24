package com.mcmouse88.stock_market_app.data.mapper

import com.mcmouse88.stock_market_app.data.local.CompanyListingEntity
import com.mcmouse88.stock_market_app.data.remote.dto.CompanyInfoDto
import com.mcmouse88.stock_market_app.domain.model.CompanyInfo
import com.mcmouse88.stock_market_app.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = this.name,
        symbol = this.symbol,
        exchange = this.exchange
    )
}

fun CompanyListing.toCompanyEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = this.name,
        symbol = this.symbol,
        exchange = this.exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = this.symbol ?: "",
        description = this.description ?: "",
        name = this.name ?: "",
        country = this.country ?: "",
        industry = this.industry ?: ""
    )
}