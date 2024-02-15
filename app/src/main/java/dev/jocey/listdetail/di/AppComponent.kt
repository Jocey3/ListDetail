package dev.jocey.listdetail.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.jocey.common.util.ApplicationScope
import dev.jocey.data.di.DataComponent
import dev.jocey.listdetail.MainActivity

@ApplicationScope
@Component(
    dependencies = [DataComponent::class],
    modules = [ViewModelModule::class]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context, dataComponent: DataComponent): AppComponent
    }
}