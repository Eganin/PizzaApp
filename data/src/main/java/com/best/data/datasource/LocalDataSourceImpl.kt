package com.best.data.datasource

import com.best.data.local.models.ProductDetail
import com.best.data.mapper.toProductInfo
import com.best.data.remote.ImagesApi
import com.best.domain.datasource.LocalDataSource
import com.best.domain.models.ProductInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    val api: ImagesApi
) : LocalDataSource {

    private val productDetails = listOf(
        ProductDetail(
            name = "Ветчина и грибы",
            description = "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
            price = "345"
        ),
        ProductDetail(
            name = "Баварские колбаски",
            description = "Баварские колбаски, ветчина,пикантная пепперони, острая чоризо,томатный соус",
            price = "345"
        ),
        ProductDetail(
            name = "Нежный лосось",
            description = "Лосось, томаты, оливки,соус песто,помидорки черри",
            price = "345"
        ),
        ProductDetail(
            name = "Гастрономический экстаз",
            description = "Ветчина,грибы, увеличинная порция зелени, соус терияки",
            price = "345"
        ),
        ProductDetail(
            name = "Ветчина и грибы",
            description = "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
            price = "345"
        ),
        ProductDetail(
            name = "Баварские колбаски",
            description = "Баварские колбаски, ветчина,пикантная пепперони, острая чоризо,томатный соус",
            price = "345"
        ),
        ProductDetail(
            name = "Нежный лосось",
            description = "Лосось, томаты, оливки,соус песто,помидорки черри",
            price = "345"
        ),
        ProductDetail(
            name = "Гастрономический экстаз",
            description = "Ветчина,грибы, увеличинная порция зелени, соус терияки",
            price = "345"
        ),
    )

    override suspend fun getCategory(): Flow<List<String>> {
        return flow {
            emit(listOf("Пицца", "Комбо", "Напитки", "Десерты"))
        }
    }

    override suspend fun getCurrentCity(): Flow<String> {
        return flow {
            emit("Москва")
        }
    }

    override suspend fun getDetailInfoProduct(): Flow<List<ProductInfo>> {
        return flow {
            withContext(defaultDispatcher) {
                val productInfoList = mutableListOf<ProductInfo>()
                productDetails.forEach { product ->
                    val imageInfo = api.getImageForPizza().imageLink
                    productInfoList.add(product.toProductInfo(imageLink = imageInfo))
                }
                emit(productInfoList)
            }
        }
    }

    override suspend fun getDetailInfoProductFromDb(): Flow<List<ProductInfo>> {
        TODO("Not yet implemented")
    }

}