package dev.jocey.domain.use_case

import dev.jocey.domain.model.NumberDomain
import dev.jocey.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNumbersUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): Flow<List<NumberDomain>> {
        return repository.getAllNumbers()
    }

}