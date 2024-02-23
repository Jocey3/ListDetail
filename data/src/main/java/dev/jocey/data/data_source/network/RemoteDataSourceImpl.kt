package dev.jocey.data.data_source.network

import dev.jocey.data.model.remote.NumberApiModel
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: NumberApi) : RemoteDataSource {
    override suspend fun getNumber(number: String): NumberApiModel {
        return api.getNumber(number)
    }
    override suspend fun getRandomNumber(): NumberApiModel {
        return api.getRandomNumber()
    }
}