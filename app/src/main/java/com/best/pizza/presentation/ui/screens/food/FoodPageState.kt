package com.best.pizza.presentation.ui.screens.food

import com.best.domain.models.OtherInfo
import com.best.domain.models.ProductInfo

internal data class FoodPageState(
    val productInfo: List<ProductInfo> = emptyList(),
    val otherInfo :OtherInfo?=null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)
