package dev.jocey.domain.repository

import dev.jocey.domain.model.NumberDomain
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAllNumbers(): Flow<List<NumberDomain>>
    suspend fun getNumber(number: String): NumberDomain
    suspend fun getRandomNumber()
}