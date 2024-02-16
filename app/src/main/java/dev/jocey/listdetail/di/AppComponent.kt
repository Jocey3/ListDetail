package dev.jocey.listdetail.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dev.jocey.common.util.ApplicationScope
import dev.jocey.data.di.DataComponent
import dev.jocey.listdetail.App

@ApplicationScope
@Component(
    dependencies = [DataComponent::class],
    modules = [AndroidInjectionModule::class, FragmentModule::class, ViewModelModule::class]
)
interface AppComponent {

    fun inject(app: App)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context, dataComponent: DataComponent): AppComponent
    }
}