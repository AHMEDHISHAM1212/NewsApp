package com.example.newsapp.data.api

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor):HttpLoggingInterceptor{
        val loggingInterceptor = HttpLoggingInterceptor {
            Log.e("api",it)
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return loggingInterceptor

    }

    @Provides
    fun provideGsonConvertorFactory() :GsonConverterFactory{
        return GsonConverterFactory.create()
    }
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ) : Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl("https://newsapi.org/")
            .build()
    }

    @Provides
    fun provideWebServices(retrofit: Retrofit) : WebServices{
        return retrofit.create(WebServices::class.java)
    }
}