package com.best.data.repository

import com.best.data.datasource.LocalDataSource
import com.best.data.datasource.RemoteDataSource
import com.best.domain.models.OtherInfo
import com.best.domain.models.ProductInfo
import com.best.domain.repository.ProductRepository
import com.best.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ProductRepository {

    override fun getProductList(fetchFromRemote: Boolean): Flow<Resource<List<ProductInfo>>> {
        return flow {
            bodyForDataLoading {
                localDataSource.getDetailInfoProductFromDb(fetchFromRemote=fetchFromRemote) {
                    remoteDataSource.getImage().imageLink
                }
            }
        }
    }

    override fun getOtherInfo(): Flow<Resource<OtherInfo>> {
        return flow {
            bodyForDataLoading {
                OtherInfo(
                    cityName = localDataSource.getCurrentCity(),
                    categories = localDataSource.getCategory()
                )
            }
        }
    }
}