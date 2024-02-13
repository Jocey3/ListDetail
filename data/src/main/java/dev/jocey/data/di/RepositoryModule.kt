package dev.jocey.data.di

import dagger.Module
import dagger.Provides
import dev.jocey.common.util.NumberRepository
import dev.jocey.data.repository.RepositoryImpl
import dev.jocey.domain.repository.Repository

@Module
class RepositoryModule {
    @Provides
    @NumberRepository
    fun provideNumberRepository(repository: RepositoryImpl): Repository {
        return repository
    }
}