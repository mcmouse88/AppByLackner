package com.mcmouse88.graphql_basic.presentation.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.graphql_basic.domain.entities.DetailCountry
import com.mcmouse88.graphql_basic.domain.entities.SimpleCountry
import com.mcmouse88.graphql_basic.domain.usecases.GetCountriesUseCase
import com.mcmouse88.graphql_basic.domain.usecases.GetDetailCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getDetailCountryUseCase: GetDetailCountryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state: StateFlow<CountriesState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    countries = getCountriesUseCase.execute(),
                    isLoading = false
                )
            }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(selectedCountry = getDetailCountryUseCase.execute(code))
            }
        }
    }

    fun dismissCountryDialog() {
        _state.update {
            it.copy(selectedCountry = null)
        }
    }

    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailCountry? = null
    )
}