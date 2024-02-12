package dev.jocey.listdetail.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.jocey.data.ioc.ApplicationScope
import dev.jocey.data.ioc.DataComponent
import dev.jocey.listdetail.MainActivity
import javax.inject.Singleton

@ApplicationScope
@Component(
    dependencies = [DataComponent::class]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context, dataComponent: DataComponent): AppComponent
    }
}