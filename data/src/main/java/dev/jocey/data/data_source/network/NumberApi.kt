package dev.jocey.data.data_source.network

import retrofit2.http.GET
import retrofit2.http.Path

interface NumberApi {

    @GET("/{number}?json")
    suspend fun getNumber(@Path("number") number: String): Modi
}

data class Modi(
    val number: Int,
    val text: String,
    val found: Boolean,
    val type: String
)