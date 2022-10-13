package com.best.data.di

import com.best.data.remote.ImagesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Domain {

    @Provides
    @Singleton
    fun provideHttpClient():OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(level = HttpLoggingInterceptor.Level.BODY)
            )
            .build()

    @Provides
    @Singleton
    fun provideImagesApi(client: OkHttpClient):ImagesApi=
        Retrofit.Builder()
            .baseUrl("https://foodish-api.herokuapp.com/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
}