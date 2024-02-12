package dev.jocey.data.ioc

import javax.inject.Qualifier
import javax.inject.Scope

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class NumberBaseUrl

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope