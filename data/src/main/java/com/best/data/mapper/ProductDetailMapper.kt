package com.best.data.mapper

import com.best.data.local.models.ProductDetail
import com.best.domain.models.ProductInfo

fun ProductDetail.toProductInfo(imageLink : String): ProductInfo{
    return ProductInfo(
        name = name,
        description=description,
        price=price,
        imageLink = imageLink
    )
}