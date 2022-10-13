package com.best.data.datasource

import com.best.data.local.models.ProductDetail
import com.best.domain.models.ProductInfo
import kotlinx.coroutines.flow.Flow

interface LocalDataSource : DataSource{
    suspend fun getCategory() : Flow<List<String>>


    suspend fun getCurrentCity() : Flow<String>

    suspend fun getDetailInfoProduct():Flow<List<ProductDetail>>

    suspend fun getDetailInfoProductFromDb(): Flow<List<ProductInfo>>

}