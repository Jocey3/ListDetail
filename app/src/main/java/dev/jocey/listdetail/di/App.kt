package dev.jocey.listdetail.di

import android.app.Application
import dev.jocey.data.di.DaggerDataComponent
import dev.jocey.data.di.DataComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(context = applicationContext, dataComponent = provideDataComponent())
    }

    private fun provideDataComponent(): DataComponent {
        return DaggerDataComponent.factory().create(applicationContext)
    }
}