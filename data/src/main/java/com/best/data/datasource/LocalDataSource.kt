package com.best.data.datasource

import com.best.domain.models.ProductInfo

interface LocalDataSource : DataSource{
    fun getCategory() : List<String>


    fun getCurrentCity() : String

    suspend fun getDetailInfoProductFromDb(fetchFromRemote : Boolean,downloadImage : suspend()->String): List<ProductInfo>

    suspend fun generateDetailInfoProduct(downloadImage : suspend()->String)
}