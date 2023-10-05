package com.mcmouse88.graphql_basic.data

import com.mcmouse88.CountriesQuery
import com.mcmouse88.CountryQuery
import com.mcmouse88.graphql_basic.domain.entities.DetailCountry
import com.mcmouse88.graphql_basic.domain.entities.SimpleCountry

fun CountryQuery.Country.toDetailCountry(): DetailCountry {
    return DetailCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        languages = languages.map { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital"
    )
}