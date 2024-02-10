package dev.jocey.listdetail.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.jocey.data.ioc.DataComponent
import dev.jocey.listdetail.MainActivity


@Component(
    dependencies = [DataComponent::class]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun dataComponent(dataComponent: DataComponent): Builder
        fun build(): AppComponent
    }
}