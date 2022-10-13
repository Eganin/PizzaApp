package com.best.domain.usecase

import com.best.domain.models.ProductInfo
import com.best.domain.repository.ProductRepository
import com.best.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetProductList(
    private val repository: ProductRepository
) {

    operator fun invoke(fetchFromRemote: Boolean) : Flow<Resource<List<ProductInfo>>>{
        return repository.getProductList(fetchFromRemote=fetchFromRemote)
    }
}