package com.best.data.di

import android.app.Application
import androidx.room.Room
import com.best.data.datasource.LocalDataSource
import com.best.data.datasource.LocalDataSourceImpl
import com.best.data.datasource.RemoteDataSource
import com.best.data.datasource.RemoteDataSourceImpl
import com.best.data.local.database.ProductDatabase
import com.best.data.remote.ImagesApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(level = HttpLoggingInterceptor.Level.BODY)
            )
            .build()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideImagesApi(client: OkHttpClient): ImagesApi{
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl("https://foodish-api.herokuapp.com/api/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideProductDatabase(app: Application): ProductDatabase {
        return Room.databaseBuilder(
            app,
            ProductDatabase::class.java,
            ProductDatabase.NAME_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(db: ProductDatabase): LocalDataSource {
        return LocalDataSourceImpl(productInfoDao = db.productInfoDao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: ImagesApi): RemoteDataSource {
        return RemoteDataSourceImpl(api = api)
    }
}