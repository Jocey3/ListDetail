package dev.jocey.listdetail

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dev.jocey.data.di.DaggerDataComponent
import dev.jocey.data.di.DataComponent
import dev.jocey.listdetail.di.DaggerAppComponent
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory()
            .create(context = applicationContext, dataComponent = provideDataComponent())
            .inject(this)
    }

    private fun provideDataComponent(): DataComponent {
        return DaggerDataComponent.factory().create(applicationContext)
    }
}