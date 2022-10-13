package com.best.data.datasource

import com.best.data.remote.ImagePizzaDto
import com.best.data.remote.ImagesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    private val api: ImagesApi
) : RemoteDataSource{
    override suspend fun getImage(): Flow<ImagePizzaDto> {
        return flow {
            withContext(defaultDispatcher){
                emit(api.getImageForPizza())
            }
        }
    }
}