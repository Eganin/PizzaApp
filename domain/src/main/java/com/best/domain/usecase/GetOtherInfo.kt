package com.best.domain.usecase

import com.best.domain.models.OtherInfo
import com.best.domain.repository.ProductRepository
import com.best.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetOtherInfo(
    private val repository: ProductRepository
) {

    operator fun invoke() : Flow<Resource<OtherInfo>>{
        return repository.getOtherInfo()
    }
}