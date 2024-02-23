package dev.jocey.data.data_source.local

import dev.jocey.data.model.local.NumberEntity

interface LocalDataSource {
    suspend fun insertAll(numbers: List<NumberEntity>)
}