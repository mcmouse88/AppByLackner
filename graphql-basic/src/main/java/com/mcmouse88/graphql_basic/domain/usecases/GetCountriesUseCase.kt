package com.mcmouse88.graphql_basic.domain.usecases

import com.mcmouse88.graphql_basic.domain.CountryClient
import com.mcmouse88.graphql_basic.domain.entities.SimpleCountry

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {

    suspend fun execute(): List<SimpleCountry> {
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }
}