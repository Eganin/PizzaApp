package com.best.data.datasource

import com.best.data.local.dao.ProductInfoDao
import com.best.data.local.models.ProductDetail
import com.best.data.mapper.toProductInfo
import com.best.data.mapper.toProductInfoEntity
import com.best.domain.models.ProductInfo
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val productInfoDao: ProductInfoDao
) : LocalDataSource {

    private val productDetails = listOf(
        ProductDetail(
            id = 1,
            name = "Ветчина и грибы",
            description = "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
            price = "345"
        ),
        ProductDetail(
            id = 2,
            name = "Баварские колбаски",
            description = "Баварские колбаски, ветчина,пикантная пепперони, острая чоризо,томатный соус",
            price = "345"
        ),
        ProductDetail(
            id = 3,
            name = "Нежный лосось",
            description = "Лосось, томаты, оливки,соус песто,помидорки черри",
            price = "345"
        ),
        ProductDetail(
            id = 4,
            name = "Гастрономический экстаз",
            description = "Ветчина,грибы, увеличинная порция зелени, соус терияки",
            price = "345"
        ),
        ProductDetail(
            id = 5,
            name = "Ветчина и грибы",
            description = "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
            price = "345"
        ),
        ProductDetail(
            id = 6,
            name = "Баварские колбаски",
            description = "Баварские колбаски, ветчина,пикантная пепперони, острая чоризо,томатный соус",
            price = "345"
        ),
        ProductDetail(
            id = 7,
            name = "Нежный лосось",
            description = "Лосось, томаты, оливки,соус песто,помидорки черри",
            price = "345"
        ),
        ProductDetail(
            id = 8,
            name = "Гастрономический экстаз",
            description = "Ветчина,грибы, увеличинная порция зелени, соус терияки",
            price = "345"
        ),
    )

    override fun getCategory(): List<String> {
        return listOf("Пицца", "Комбо", "Напитки", "Десерты")
    }

    override fun getCurrentCity(): String {
        return "Москва"
    }

    override suspend fun getDetailInfoProductFromDb(imageLink: String): List<ProductInfo> =
        withContext(defaultDispatcher) {
            val result = productInfoDao.getAllProductInfo().map { it.toProductInfo(imageLink = imageLink) }
            val answer = result.ifEmpty {
                generateDetailInfoProduct(imageLink = imageLink)
                productInfoDao.getAllProductInfo().map { it.toProductInfo(imageLink = imageLink) }
            }
            answer
        }

    override suspend fun generateDetailInfoProduct(imageLink: String) =
        withContext(defaultDispatcher) {
            val listProductInfo = productDetails.map {
                it.toProductInfo(imageLink = imageLink)
                    .toProductInfoEntity()
            }
            productInfoDao.insertProductInfo(productInfoEntities = listProductInfo)
        }

}