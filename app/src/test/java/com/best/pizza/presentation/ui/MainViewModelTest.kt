package com.best.pizza.presentation.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.best.domain.models.OtherInfo
import com.best.domain.models.ProductInfo
import com.best.domain.repository.ProductRepository
import com.best.domain.usecase.GetOtherInfo
import com.best.domain.usecase.GetProductList
import com.best.domain.usecase.ProductsUseCases
import com.best.domain.util.Resource
import com.best.pizza.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
internal class MainViewModelTest {
    private var viewModel: MainViewModel? = null

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private fun createViewModel(state: State) {
        val testRepository = TestRepository(state = state)
        viewModel = MainViewModel(
            productsUseCases = ProductsUseCases(
                getProductList = GetProductList(repository = testRepository),
                getOtherInfo = GetOtherInfo(repository = testRepository)
            )
        )
    }

    @After
    fun teardown() {
        viewModel = null
    }


    @Test
    fun `success loading data`() {
        createViewModel(state = State.SUCCESS)
        viewModel?.init()
        val productInfo = viewModel?.state?.productInfo
        val otherInfo = viewModel?.state?.otherInfo
        assertThat(productInfo).isNotEmpty()
        assertThat(otherInfo).isNotNull()
        assertThat(viewModel?.state?.error).isNull()
        assertThat(viewModel?.state?.isLoading).isFalse()
    }

    @Test
    fun `error loading data`() {
        createViewModel(state = State.ERROR)
        viewModel?.init()
        assertThat(viewModel?.state?.error).isNotEmpty()
        assertThat(viewModel?.state?.isLoading).isFalse()
    }

    @Test
    fun `test swipe refresh success loading data`(){
        createViewModel(state = State.SUCCESS)
        viewModel?.onEvent(event = MainPageEvent.Refresh)
        val productInfo = viewModel?.state?.productInfo
        val otherInfo = viewModel?.state?.otherInfo
        assertThat(productInfo).isNotEmpty()
        assertThat(otherInfo).isNotNull()
        assertThat(viewModel?.state?.error).isNull()
        assertThat(viewModel?.state?.isLoading).isFalse()
    }

    @Test
    fun `test swipe refresh error loading data`(){
        createViewModel(state = State.ERROR)
        viewModel?.onEvent(event = MainPageEvent.Refresh)
        assertThat(viewModel?.state?.error).isNotEmpty()
        assertThat(viewModel?.state?.isLoading).isFalse()
    }

    enum class State {
        SUCCESS,
        ERROR,
    }

    private class TestRepository(private val state: State) : ProductRepository {
        override fun getProductList(fetchFromRemote: Boolean): Flow<Resource<List<ProductInfo>>> {
            return flow {
                when (state) {
                    State.SUCCESS -> {
                        emit(
                            Resource.Success(
                                data = listOf(
                                    ProductInfo(
                                        name = "Test",
                                        description = "test description",
                                        price = "999",
                                        imageLink = "https://image"
                                    )
                                )
                            )
                        )
                    }

                    State.ERROR -> {
                        emit(Resource.Error(message = "Unknown Error"))
                    }
                }
            }
        }

        override fun getOtherInfo(): Flow<Resource<OtherInfo>> {
            return flow {
                when (state) {
                    State.SUCCESS -> {
                        emit(
                            Resource.Success(
                                data = OtherInfo(
                                    cityName = "Moskow",
                                    categories = listOf("food", "drink")
                                )
                            )
                        )
                    }

                    State.ERROR -> {
                        emit(Resource.Error(message = "Unknown Error"))
                    }
                }
            }
        }
    }
}