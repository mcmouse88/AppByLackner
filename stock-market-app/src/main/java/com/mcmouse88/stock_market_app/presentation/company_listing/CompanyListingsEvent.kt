package com.mcmouse88.stock_market_app.presentation.company_listing

sealed interface CompanyListingsEvent {
    data object Refresh : CompanyListingsEvent
    data class OnSearchQueryChange(val query: String) : CompanyListingsEvent
}