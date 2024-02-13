package dev.jocey.common.util

import javax.inject.Qualifier
import javax.inject.Scope

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class NumberBaseUrl

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class NumberRepository

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IODispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher



@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope