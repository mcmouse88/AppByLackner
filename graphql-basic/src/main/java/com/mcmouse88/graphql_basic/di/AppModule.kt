package com.mcmouse88.graphql_basic.di

import com.apollographql.apollo3.ApolloClient
import com.mcmouse88.graphql_basic.data.ApolloCountryClient
import com.mcmouse88.graphql_basic.domain.CountryClient
import com.mcmouse88.graphql_basic.domain.usecases.GetCountriesUseCase
import com.mcmouse88.graphql_basic.domain.usecases.GetDetailCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object AppModule {

    @[Provides Singleton]
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @[Provides Singleton]
    fun provideCountryClient(
        apolloClient: ApolloClient
    ): CountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @[Provides Singleton]
    fun provideGetCountriesUseCase(
        countryClient: CountryClient
    ): GetCountriesUseCase {
        return GetCountriesUseCase(countryClient)
    }

    @[Provides Singleton]
    fun provideGetDetailCountryUseCase(
        countryClient: CountryClient
    ): GetDetailCountryUseCase {
        return GetDetailCountryUseCase(countryClient)
    }
}