package dev.jocey.listdetail.di

import android.app.Application
import dev.jocey.data.ioc.DaggerDataComponent
import dev.jocey.data.ioc.DataComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().context(applicationContext)
            .dataComponent(provideDataComponent()).build()
    }

    private fun provideDataComponent(): DataComponent {
        return DaggerDataComponent.builder().context(applicationContext).build()
    }
}