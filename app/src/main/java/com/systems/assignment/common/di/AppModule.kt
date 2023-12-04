package com.systems.assignment.common.di

import com.systems.assignment.AppControl
import com.systems.assignment.common.AppEnvironment
import com.systems.assignment.common.data.remote.ApiErrorHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplicationContext() = AppControl()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = AppEnvironment.getLogsLevel()
        return OkHttpClient.Builder()
            .connectTimeout(120L, TimeUnit.SECONDS)
            .readTimeout(120L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(AppEnvironment.getServerUrl())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiErrorHandler(): ApiErrorHandle {
        return ApiErrorHandle()
    }
}