package com.best.data.datasource

import com.best.data.local.database.ProductDatabase
import com.best.data.local.models.ProductDetail
import com.best.data.mapper.toProductInfo
import com.best.domain.models.ProductInfo
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    db: ProductDatabase
) : LocalDataSource {

    private val productInfoDao = db.productInfoDao

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

    override fun getCategory(): List<String> {
        return listOf("Пицца", "Комбо", "Напитки", "Десерты")
    }

    override fun getCurrentCity(): String {
        return "Москва"
    }

    override fun getDetailInfoProduct(): List<ProductDetail> {
        return productDetails
    }

    override suspend fun getDetailInfoProductFromDb():List<ProductInfo> {
        return withContext(defaultDispatcher){
            productInfoDao.getAllProductInfo().map { it.toProductInfo() }
        }
    }

}