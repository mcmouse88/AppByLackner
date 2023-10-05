package com.mcmouse88.graphql_basic.domain.usecases

import com.mcmouse88.graphql_basic.domain.CountryClient
import com.mcmouse88.graphql_basic.domain.entities.DetailCountry

class GetDetailCountryUseCase(
    private val countryClient: CountryClient
) {

    suspend fun execute(code: String): DetailCountry? {
        return countryClient.getCountry(code)
    }
}