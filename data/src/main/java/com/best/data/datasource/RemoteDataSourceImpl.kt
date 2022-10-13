package com.best.data.datasource

import android.util.Log
import com.best.data.remote.ImagePizzaDto
import com.best.data.remote.ImagesApi
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    private val api: ImagesApi
) : RemoteDataSource{
    override suspend fun getImage(): ImagePizzaDto {
        return withContext(defaultDispatcher){
            api.getImageForPizza()
        }
    }
}