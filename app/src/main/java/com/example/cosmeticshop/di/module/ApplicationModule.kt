package com.example.cosmeticshop.di.module

import com.example.cosmeticshop.BuildConfig
import com.example.cosmeticshop.data.api.ApiHelper
import com.example.cosmeticshop.data.api.ApiHelperImpl
import com.example.cosmeticshop.data.api.ApiService
import com.example.cosmeticshop.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dependency Injector class which is responsible for providing essential info
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    // returns Base URL
    @Provides
    fun provideBaseUrl() = BASE_URL

    // returns instance of okHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    // returns instance of retrofit
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    // returns instance of API service
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    // returns instance of API Helper
    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}