package dev.jocey.domain.use_case

import dev.jocey.domain.repository.Repository
import javax.inject.Inject

class GetRandomNumber @Inject constructor(private val repository: Repository) {
    suspend fun invoke() {
        return repository.getRandomNumber()
    }
}