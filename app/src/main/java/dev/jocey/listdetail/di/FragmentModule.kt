package dev.jocey.listdetail.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.jocey.feature_home.HomeFragment


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun splashFragment(): HomeFragment
}