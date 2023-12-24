package com.mcmouse88.stock_market_app.presentation.company_info

import com.mcmouse88.stock_market_app.domain.model.CompanyInfo
import com.mcmouse88.stock_market_app.domain.model.IntraDayInfo

data class CompanyInfoState(
    val stockInfo: List<IntraDayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
