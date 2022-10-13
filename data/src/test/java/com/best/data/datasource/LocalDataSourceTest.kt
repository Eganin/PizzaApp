package com.best.data.datasource

import com.best.data.TestData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
internal class LocalDataSourceTest : TestData(){

    @Test
    fun `test get category`() {
        assertThat(localDataSource.getCategory()).isEqualTo(testcategoriesList)
    }

    @Test
    fun `test get city name`() {
        assertThat(localDataSource.getCurrentCity()).isEqualTo(testcityName)
    }

    @Test
    fun `test get detail info for product if link is not empty`() = runTest {
        assertThat(localDataSource.getDetailInfoProductFromDb(fetchFromRemote = false){""}).isEqualTo(
            testproductDetailsList
        )
    }

    @Test
    fun `test get detail info for product if link is empty`() = runTest {
        assertThat(localDataSource.getDetailInfoProductFromDb(fetchFromRemote = false){"https://"}).isEqualTo(
            testproductDetailsList.map { it.copy(imageLink = "https://") })
    }

    @Test
    fun `test get detail info for product if database is empty`() = runTest {
        testDao.clearProductInfo()
        assertThat(localDataSource.getDetailInfoProductFromDb(fetchFromRemote = false){""}).isEqualTo(
            testproductDetailsList
        )
    }

}