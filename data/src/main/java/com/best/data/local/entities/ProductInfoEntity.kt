package com.best.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class ProductInfoEntity(
    @PrimaryKey
    val id: Int? = null,
    val name : String,
    val description : String,
    val price : String,
    val imageLink : String
)
