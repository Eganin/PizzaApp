package com.best.domain.repository

import com.best.domain.models.OtherInfo
import com.best.domain.models.ProductInfo
import com.best.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductList(fetchFromRemote: Boolean): Flow<Resource<List<ProductInfo>>>

    fun getOtherInfo(): Flow<Resource<OtherInfo>>
}