package com.best.data.local.entities

import androidx.room.Entity

@Entity
data class ProductInfoEntity(
    val name : String,
    val description : String,
    val price : String,
    val imageLink : String
)
