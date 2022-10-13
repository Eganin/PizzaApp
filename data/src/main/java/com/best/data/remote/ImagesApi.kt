package com.best.data.remote

import retrofit2.http.GET

interface ImagesApi {

    @GET("images/pizza")
    suspend fun getImageForPizza():ImagePizzaDto
}