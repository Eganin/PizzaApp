package com.best.data.di

import android.app.Application
import androidx.room.Room
import com.best.data.datasource.LocalDataSource
import com.best.data.datasource.LocalDataSourceImpl
import com.best.data.datasource.RemoteDataSource
import com.best.data.datasource.RemoteDataSourceImpl
import com.best.data.local.database.ProductDatabase
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
object DomainModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(level = HttpLoggingInterceptor.Level.BODY)
            )
            .build()

    @Provides
    @Singleton
    fun provideImagesApi(client: OkHttpClient): ImagesApi =
        Retrofit.Builder()
            .baseUrl("https://foodish-api.herokuapp.com/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()

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
        return LocalDataSourceImpl(db = db)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: ImagesApi): RemoteDataSource {
        return RemoteDataSourceImpl(api = api)
    }
}