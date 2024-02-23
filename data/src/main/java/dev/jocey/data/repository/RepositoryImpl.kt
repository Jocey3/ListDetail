package dev.jocey.data.repository

import dev.jocey.common.util.IODispatcher
import dev.jocey.data.data_source.local.LocalDataSource
import dev.jocey.data.data_source.network.RemoteDataSource
import dev.jocey.data.mapper.NumberMapper
import dev.jocey.domain.model.NumberDomain
import dev.jocey.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mapper: NumberMapper,
    @IODispatcher
    private val iODispatcher: CoroutineDispatcher
) : Repository {
    override suspend fun getNumber(number: String): NumberDomain {
        return withContext(iODispatcher) {
            val numberRemoteModel = remoteDataSource.getNumber(number)
            localDataSource.insertAll(listOf(mapper.mapFromApiToEntity(numberRemoteModel)))
            mapper.mapFromApiToDomain(numberRemoteModel)
        }
    }
}