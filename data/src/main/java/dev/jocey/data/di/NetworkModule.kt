package dev.jocey.data.di

import dagger.Module
import dagger.Provides
import dev.jocey.data.data_source.network.NumberApi
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
}
