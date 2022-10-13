package com.best.pizza.presentation.di

import com.best.domain.repository.ProductRepository
import com.best.domain.usecase.GetOtherInfo
import com.best.domain.usecase.GetProductList
import com.best.domain.usecase.ProductsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductsUseCases(
        productRepository: ProductRepository
    ): ProductsUseCases {
        return ProductsUseCases(
            getOtherInfo = GetOtherInfo(repository = productRepository),
            getProductList = GetProductList(repository = productRepository)
        )
    }
}