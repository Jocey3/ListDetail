package dev.jocey.data.data_source.network

import dev.jocey.data.model.remote.NumberApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface NumberApi {
    @GET("/{number}?json")
    suspend fun getNumber(@Path("number") number: String): NumberApiModel

    @GET("/random/math?json")
    suspend fun getRandomNumber(): NumberApiModel
}
