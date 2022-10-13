package com.best.domain.datasource

import com.best.domain.models.ProductInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    val defaultDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

    suspend fun getCategory() : Flow<List<String>>


    suspend fun getCurrentCity() : Flow<String>

    suspend fun getDetailInfoProduct():Flow<List<ProductInfo>>

    suspend fun getDetailInfoProductFromDb(): Flow<List<ProductInfo>>

}