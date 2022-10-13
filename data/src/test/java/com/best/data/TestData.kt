package com.best.data

import com.best.data.datasource.LocalDataSource
import com.best.data.datasource.LocalDataSourceImpl
import com.best.data.datasource.RemoteDataSource
import com.best.data.datasource.RemoteDataSourceImpl
import com.best.data.local.dao.ProductInfoDao
import com.best.data.local.entities.ProductInfoEntity
import com.best.data.remote.ImagePizzaDto
import com.best.data.remote.ImagesApi
import com.best.domain.models.ProductInfo

internal open class TestData {

    val testLink = "https://image/pizza.jpg"
    val testApi: ImagesApi = TestApi(testLink=testLink)
    val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl(api = testApi)

    val testDao: ProductInfoDao = TestDao()
    val localDataSource: LocalDataSource = LocalDataSourceImpl(productInfoDao = testDao)

    val testcategoriesList = listOf("Пицца", "Комбо", "Напитки", "Десерты")
    val testcityName = "Москва"
    val testproductDetailsList = listOf(
        ProductInfo(
            imageLink = "",
            name = "Ветчина и грибы",
            description = "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
            price = "345"
        ),
        ProductInfo(
            imageLink = "",
            name = "Баварские колбаски",
            description = "Баварские колбаски, ветчина,пикантная пепперони, острая чоризо,томатный соус",
            price = "345"
        ),
        ProductInfo(
            imageLink = "",
            name = "Нежный лосось",
            description = "Лосось, томаты, оливки,соус песто,помидорки черри",
            price = "345"
        ),
        ProductInfo(
            imageLink = "",
            name = "Гастрономический экстаз",
            description = "Ветчина,грибы, увеличинная порция зелени, соус терияки",
            price = "345"
        ),
        ProductInfo(
            imageLink = "",
            name = "Ветчина и грибы",
            description = "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
            price = "345"
        ),
        ProductInfo(
            imageLink = "",
            name = "Баварские колбаски",
            description = "Баварские колбаски, ветчина,пикантная пепперони, острая чоризо,томатный соус",
            price = "345"
        ),
        ProductInfo(
            imageLink = "",
            name = "Нежный лосось",
            description = "Лосось, томаты, оливки,соус песто,помидорки черри",
            price = "345"
        ),
        ProductInfo(
            imageLink = "",
            name = "Гастрономический экстаз",
            description = "Ветчина,грибы, увеличинная порция зелени, соус терияки",
            price = "345"
        ),
    )

    private class TestDao : ProductInfoDao {

        private val listProducts = mutableListOf<ProductInfoEntity>()

        override suspend fun insertProductInfo(productInfoEntities: List<ProductInfoEntity>) {
            listProducts.addAll(productInfoEntities)
        }

        override suspend fun clearProductInfo() = listProducts.clear()


        override suspend fun getAllProductInfo(): List<ProductInfoEntity> = listProducts


    }

    private class TestApi(private val testLink : String) : ImagesApi{
        override suspend fun getImageForPizza(): ImagePizzaDto {
            return ImagePizzaDto(
                imageLink = testLink
            )
        }

    }
}