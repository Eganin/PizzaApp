package com.best.pizza.presentation.ui.screens.food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.best.domain.usecase.ProductsUseCases
import com.best.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FoodViewModel @Inject constructor(
    private val productsUseCases: ProductsUseCases
) : ViewModel() {

    var state by mutableStateOf(FoodPageState())
        private set

    fun init() {
        downloadData(fetchFromRemote = false)
    }

    fun onEvent(event: FoodPageEvent) {
        when (event) {
            is FoodPageEvent.Refresh -> {
                downloadData(fetchFromRemote = true)
            }
        }
    }

    private fun downloadData(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            // load products
            productsUseCases.getProductList(fetchFromRemote = fetchFromRemote).collect { result ->
                wrapperForHandlerResource(result = result) {
                    state = state.copy(productInfo = it, isLoading = false, error = null)
                }
            }

            //load otherInfo
            productsUseCases.getOtherInfo().collect { result ->
                wrapperForHandlerResource(result = result) {
                    state = state.copy(otherInfo = it, isLoading = false, error = null)
                }
            }
        }
    }

    private fun <T> wrapperForHandlerResource(
        result: Resource<T>,
        onStateChangeSuccess: (T) -> Unit
    ) {
        when (result) {
            is Resource.Success -> {
                result.data?.let {
                    onStateChangeSuccess(it)
                }
            }

            is Resource.Error -> {
                result.message?.let {
                    state = state.copy(
                        isLoading = false,
                        error = it
                    )
                }
            }

            is Resource.Loading -> {
                state = state.copy(isLoading = true)
            }
        }
    }
}