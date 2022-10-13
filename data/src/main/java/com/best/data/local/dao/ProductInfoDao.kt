package com.best.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.best.data.local.entities.ProductInfoEntity

@Dao
interface ProductInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductInfo(productInfoEntities: List<ProductInfoEntity>)

    @Query("DELETE FROM productinfoentity")
    suspend fun clearProductInfo()

    @Query("SELECT * FROM productinfoentity")
    suspend fun getAllProductInfo(): List<ProductInfoEntity>
}