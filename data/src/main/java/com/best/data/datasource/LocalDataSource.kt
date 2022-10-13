package com.best.data.datasource

import com.best.domain.models.ProductInfo

interface LocalDataSource : DataSource{
    fun getCategory() : List<String>


    fun getCurrentCity() : String

    suspend fun getDetailInfoProductFromDb(imageLink : String): List<ProductInfo>

    suspend fun generateDetailInfoProduct(imageLink: String)
}