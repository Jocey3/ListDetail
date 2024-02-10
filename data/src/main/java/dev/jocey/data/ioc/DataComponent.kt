package dev.jocey.data.ioc

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.jocey.data.data_source.network.NumberApi


@Component(modules = [NetworkModule::class])
interface DataComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): DataComponent
    }

    fun provideApi(): NumberApi
}