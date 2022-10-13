package com.best.data.datasource

import com.best.data.remote.ImagePizzaDto

interface RemoteDataSource :DataSource{
    suspend fun getImage(): ImagePizzaDto
}