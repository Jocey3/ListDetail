package dev.jocey.listdetail.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.jocey.common.di.CommonComponent
import dev.jocey.common.util.ApplicationScope
import dev.jocey.data.di.DataComponent
import dev.jocey.listdetail.MainActivity
import dev.jocey.listdetail.temp.HomeViewModel

@ApplicationScope
@Component(
    dependencies = [DataComponent::class, CommonComponent::class]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun myViewModel(): HomeViewModel
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            dataComponent: DataComponent,
            commonComponent: CommonComponent
        ): AppComponent
    }
}