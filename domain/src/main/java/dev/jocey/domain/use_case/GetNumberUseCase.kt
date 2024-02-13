package dev.jocey.domain.use_case

import dev.jocey.domain.model.NumberDomain
import dev.jocey.domain.repository.Repository
import javax.inject.Inject

class GetNumberUseCase @Inject constructor(private val repository: Repository) {
    suspend fun invoke(number: String): NumberDomain {
       return repository.getNumber(number)
    }
}