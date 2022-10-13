package com.best.data.repository

import com.best.data.datasource.LocalDataSource
import com.best.data.datasource.RemoteDataSource
import com.best.data.local.database.ProductDatabase
import com.best.data.local.entities.ProductInfoEntity
import com.best.domain.models.OtherInfo
import com.best.domain.models.ProductInfo
import com.best.domain.repository.ProductRepository
import com.best.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    db: ProductDatabase
) : ProductRepository {

    private val productInfoDao = db.productInfoDao

    override fun getProductList(fetchFromRemote: Boolean): Flow<Resource<List<ProductInfo>>> {
        val listProductInfo = mutableListOf<ProductInfoEntity>()
        return flow {
            bodyForDataLoading {
                if (fetchFromRemote) {
                    localDataSource.getDetailInfoProduct().forEach {
                        val remoteImage = remoteDataSource.getImage()
                        listProductInfo.add(
                            ProductInfoEntity(
                                name = it.name,
                                description = it.description,
                                price = it.price,
                                imageLink = remoteImage.imageLink
                            )
                        )
                    }
                    productInfoDao.insertProductInfo(productInfoEntities = listProductInfo)
                }

                localDataSource.getDetailInfoProductFromDb()
            }
        }
    }

    override fun getOtherInfo(): Flow<Resource<OtherInfo>> {
        return flow {
            bodyForDataLoading {
                OtherInfo(
                    cityName=localDataSource.getCurrentCity(),
                    categories = localDataSource.getCategory()
                )
            }
        }
    }
}