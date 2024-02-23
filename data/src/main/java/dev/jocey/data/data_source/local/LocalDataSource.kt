package dev.jocey.data.data_source.local

import dev.jocey.data.model.local.NumberEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllNumbers(): Flow<List<NumberEntity>>
    suspend fun insertAll(numbers: List<NumberEntity>)
}