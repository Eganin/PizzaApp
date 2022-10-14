package com.best.pizza.presentation.ui

import com.best.domain.models.OtherInfo
import com.best.domain.models.ProductInfo

data class MainPageState(
    val productInfo: List<ProductInfo> = emptyList(),
    val otherInfo :OtherInfo?=null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)
