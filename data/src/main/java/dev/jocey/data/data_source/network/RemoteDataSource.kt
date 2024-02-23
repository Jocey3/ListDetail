package dev.jocey.data.data_source.network

import dev.jocey.data.model.remote.NumberApiModel

interface RemoteDataSource {
    suspend fun getNumber(number: String): NumberApiModel
    suspend fun getRandomNumber(): NumberApiModel
}