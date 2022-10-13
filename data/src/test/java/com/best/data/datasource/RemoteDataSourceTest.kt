package com.best.data.datasource

import com.best.data.remote.ImagePizzaDto
import com.best.data.remote.ImagesApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
internal class RemoteDataSourceTest {

    private val testLink = "https://image/pizza.jpg"
    private val testApi: ImagesApi = TestApi(testLink=testLink)
    private val remoteDataSource:RemoteDataSource = RemoteDataSourceImpl(api = testApi)

    @Test
    fun `test get image`() = runTest{
        assertThat(remoteDataSource.getImage().imageLink).isEqualTo(testLink)
    }
}

private class TestApi(private val testLink : String) : ImagesApi{
    override suspend fun getImageForPizza(): ImagePizzaDto {
        return ImagePizzaDto(
            imageLink = testLink
        )
    }

}