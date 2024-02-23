package dev.jocey.data.data_source.local

import dev.jocey.data.model.local.NumberEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val numberDao: NumberDao) : LocalDataSource {
    override fun getAllNumbers(): Flow<List<NumberEntity>> {
        return numberDao.getAll()
    }

    override suspend fun insertAll(numbers: List<NumberEntity>) {
        numberDao.insertAll(numbers)
    }
}