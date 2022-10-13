package com.best.data.datasource

import com.best.data.TestData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
internal class RemoteDataSourceTest :TestData(){

    @Test
    fun `test get image`() = runTest{
        assertThat(remoteDataSource.getImage().imageLink).isEqualTo(testLink)
    }
}