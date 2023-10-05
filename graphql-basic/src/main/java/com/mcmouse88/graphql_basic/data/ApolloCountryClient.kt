package com.mcmouse88.graphql_basic.data

import com.apollographql.apollo3.ApolloClient
import com.mcmouse88.CountriesQuery
import com.mcmouse88.CountryQuery
import com.mcmouse88.graphql_basic.domain.CountryClient
import com.mcmouse88.graphql_basic.domain.entities.DetailCountry
import com.mcmouse88.graphql_basic.domain.entities.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailCountry()
    }
}