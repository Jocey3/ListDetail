package dev.jocey.data.di

import dagger.Module
import dagger.Provides
import dev.jocey.common.util.DefaultDispatcher
import dev.jocey.common.util.IODispatcher
import dev.jocey.data.data_source.network.NumberApi
import dev.jocey.data.data_source.network.RemoteDataSource
import dev.jocey.data.data_source.network.RemoteDataSourceImpl
import dev.jocey.data.repository.RepositoryImpl
import dev.jocey.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @dev.jocey.common.util.NumberBaseUrl
    fun provideNumberBaseUrl(): String = "http://numbersapi.com"

    @Singleton
    @Provides
    fun provideNumberRetrofit(@dev.jocey.common.util.NumberBaseUrl numberBaseUrl: String): Retrofit {

        val httpLogsInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpBuilder = OkHttpClient.Builder().apply {
            addInterceptor(httpLogsInterceptor)
        }
        return Retrofit.Builder().baseUrl(numberBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

    @Singleton
    @Provides
    fun provideNumberApi(retrofit: Retrofit): NumberApi {
        return retrofit.create(NumberApi::class.java)
    }

    @Singleton
    @IODispatcher
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @DefaultDispatcher
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    fun provideRemoteDataSourceImpl(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource {
        return remoteDataSourceImpl
    }

    @Provides
    fun provideNumberRepository(repository: RepositoryImpl): Repository {
        return repository
    }
}
