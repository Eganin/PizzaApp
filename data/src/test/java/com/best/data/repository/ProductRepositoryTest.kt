package com.best.data.repository

import com.best.data.TestData
import com.best.domain.models.OtherInfo
import com.best.domain.repository.ProductRepository
import com.best.domain.util.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
internal class ProductRepositoryTest : TestData() {
    private val repository: ProductRepository = ProductRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )

    @Test
    fun `get product list if remote false`() = runTest {
        repository.getProductList(fetchFromRemote = false).collect {
            if (it is Resource.Success) {
                assertThat(it.data).isEqualTo(testproductDetailsList)
            }
        }
    }

    @Test
    fun `get product list if remote true`() = runTest {
        repository.getProductList(fetchFromRemote = true).collect {
            if (it is Resource.Success) {
                assertThat(it.data).isEqualTo(testproductDetailsList.map { it.copy(imageLink = testLink) })
            }
        }
    }

    @Test
    fun `get other info`() = runTest {
        repository.getOtherInfo().collect {
            if (it is Resource.Success) {
                assertThat(it.data).isEqualTo(OtherInfo(
                    cityName=testcityName,
                    categories=testcategoriesList
                ))
            }
        }
    }
}