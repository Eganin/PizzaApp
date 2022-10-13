package com.best.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagePizzaDto(
    @SerialName("image")
    val imageLink:String
)
