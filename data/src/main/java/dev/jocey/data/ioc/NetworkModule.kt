package dev.jocey.data.ioc

import dagger.Module
import dagger.Provides
import dev.jocey.data.data_source.network.NumberApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @NumberBaseUrl
    fun provideNumberBaseUrl(): String = "http://numbersapi.com"

    @Singleton
    @Provides
    fun provideNumberRetrofit(@NumberBaseUrl numberBaseUrl: String): Retrofit {

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
