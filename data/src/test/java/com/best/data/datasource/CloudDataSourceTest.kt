package com.best.data.datasource

import com.best.data.local.dao.ProductInfoDao
import com.best.data.local.entities.ProductInfoEntity
import com.best.data.local.models.ProductDetail
import com.best.data.mapper.toProductInfo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CloudDataSourceTest {

    private val testDao :ProductInfoDao=TestDao()
    private val localDataSource: LocalDataSource=LocalDataSourceImpl(productInfoDao = testDao)

    private val categoriesList = listOf("Пицца", "Комбо", "Напитки", "Десерты")
    private val cityName = "Москва"
    private val productDetailsList = listOf(
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

    @Test
    fun `test get category`(){
        assertThat(localDataSource.getCategory()).isEqualTo(categoriesList)
    }

    @Test
    fun `test get city name`(){
        assertThat(localDataSource.getCurrentCity()).isEqualTo(cityName)
    }

    @Test
    fun `test get detail info for product`(){
        assertThat(localDataSource.getDetailInfoProduct()).isEqualTo(productDetailsList)
    }

    @Test
    fun `test get correct detail info product from database`()= runTest{
        val listProductInfo = mutableListOf<ProductInfoEntity>()
        localDataSource.getDetailInfoProduct().forEach {
            val remoteImage= "https://"
            listProductInfo.add(
                ProductInfoEntity(
                    name = it.name,
                    description = it.description,
                    price = it.price,
                    imageLink = remoteImage
                )
            )
        }
        testDao.insertProductInfo(productInfoEntities = listProductInfo)
        val allData = localDataSource.getDetailInfoProductFromDb()
        assertThat(allData).isEqualTo(listProductInfo.map { it.toProductInfo() })
    }

}

private class TestDao : ProductInfoDao {

    private val listProducts = mutableListOf<ProductInfoEntity>()

    override suspend fun insertProductInfo(productInfoEntities: List<ProductInfoEntity>){
        listProducts.addAll(productInfoEntities)
    }

    override suspend fun clearProductInfo() = listProducts.clear()


    override suspend fun getAllProductInfo(): List<ProductInfoEntity> = listProducts


}