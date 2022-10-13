package com.best.data.datasource

import com.best.data.remote.ImagePizzaDto
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource :DataSource{
    suspend fun getImage(): Flow<ImagePizzaDto>
}