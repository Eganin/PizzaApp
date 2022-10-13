package com.best.data.datasource

import com.best.data.local.models.ProductDetail
import com.best.domain.models.ProductInfo

interface LocalDataSource : DataSource{
    fun getCategory() : List<String>


    fun getCurrentCity() : String

    fun getDetailInfoProduct():List<ProductDetail>

    suspend fun getDetailInfoProductFromDb(): List<ProductInfo>


}