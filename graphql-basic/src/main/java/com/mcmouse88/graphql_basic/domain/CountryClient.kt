package com.mcmouse88.graphql_basic.domain

import com.mcmouse88.graphql_basic.domain.entities.DetailCountry
import com.mcmouse88.graphql_basic.domain.entities.SimpleCountry

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailCountry?
}