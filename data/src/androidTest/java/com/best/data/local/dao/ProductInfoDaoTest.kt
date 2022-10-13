package com.best.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.best.data.local.database.ProductDatabase
import com.best.data.local.entities.ProductInfoEntity
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
internal class ProductInfoDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: ProductDatabase

    private lateinit var dao: ProductInfoDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.productInfoDao
    }

    @After
    fun teardown() {
        database.close()
    }

    private val item = ProductInfoEntity(
        id=1,
        name = "Ветчина и грибы",
        description = "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
        price = "345",
        imageLink = "https://"
    )

    private val item2 = ProductInfoEntity(
        id=2,
        name = "Ветчина",
        description = "Увеличинная порция моцареллы, томатный соус",
        price = "345",
        imageLink = "https://"
    )

    private val insertList = listOf(item, item2)

    @Test
    fun insertProductInfo() = runTest {

        dao.insertProductInfo(productInfoEntities = insertList)

        val allItem = dao.getAllProductInfo()

        assertThat(allItem).contains(item)
        assertThat(allItem).contains(item2)
    }

    @Test
    fun clearProductInfo() = runTest {
        dao.clearProductInfo()
        val allItem = dao.getAllProductInfo()
        assertThat(allItem).isEmpty()
    }
}