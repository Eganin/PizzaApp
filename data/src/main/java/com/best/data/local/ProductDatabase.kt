package com.best.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.best.data.local.entities.ProductInfoEntity

@Database(
    entities = [
        ProductInfoEntity::class
    ],
    version = 1
)
abstract class ProductDatabase : RoomDatabase(){

    companion object{
        const val NAME_DATABASE="products.db"
    }
}