package com.best.data.datasource

import com.best.data.local.database.ProductDatabase
import com.best.data.local.models.ProductDetail
import com.best.data.mapper.toProductInfo
import com.best.domain.models.ProductInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override suspend fun getDetailInfoProduct(): Flow<List<ProductDetail>> {
        return flow {
            emit(productDetails)
        }
    }

    override suspend fun getDetailInfoProductFromDb(): Flow<List<ProductInfo>> {
        return flow {
            withContext(defaultDispatcher){
                emit(productInfoDao.getAllProductInfo().map { it.toProductInfo() })
            }
        }
    }

}