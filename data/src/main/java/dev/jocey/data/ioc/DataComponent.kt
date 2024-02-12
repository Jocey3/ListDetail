package dev.jocey.data.ioc

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.jocey.data.data_source.network.NumberApi
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface DataComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DataComponent
    }

    fun provideApi(): NumberApi
}