package dev.jocey.common.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DispatchersModule::class])
interface CommonComponent {
    @Component.Factory
    interface Factory {
        fun create(): CommonComponent
    }
}