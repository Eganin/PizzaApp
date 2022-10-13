package com.best.data.mapper

import com.best.data.local.entities.ProductInfoEntity
import com.best.domain.models.ProductInfo

fun ProductInfoEntity.toProductInfo(): ProductInfo {
    return ProductInfo(
        name = name,
        description = description,
        price = price,
        imageLink=imageLink
    )
}

fun ProductInfo.toProductInfoEntity() : ProductInfoEntity{
    return ProductInfoEntity(
        name = name,
        description = description,
        price = price,
        imageLink = imageLink
    )
}