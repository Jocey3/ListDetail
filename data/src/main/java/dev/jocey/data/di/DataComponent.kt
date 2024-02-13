package dev.jocey.data.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.jocey.domain.repository.Repository
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface DataComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DataComponent
    }
}