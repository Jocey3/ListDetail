package dev.jocey.domain.repository

import dev.jocey.domain.model.NumberDomain

interface Repository {
    suspend fun getNumber(number: String): NumberDomain
}