package com.huyngo.projecthuyandroid.myconverter.data.remote.remote

import com.huyngo.projecthuyandroid.myconverter.EXCHANGE_RATE_API_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.picasso.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val timeoutRead = 10 // 10 seconds
private const val timeoutConnect = 10 // 10 seconds
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val ACCESS_KEY = "apikey" // Parameter name for API key
private const val API_KEY = "REPLACE_API_KEY_HERE"

@Singleton
class ServiceGenerator @Inject constructor() {
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
            .header(contentType, contentTypeValue)
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }

    private val accessKeyInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val urlWithAccessKey = originalRequest.url.newBuilder()
            .addQueryParameter(ACCESS_KEY, API_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(urlWithAccessKey)
            .build()

        chain.proceed(newRequest)
    }


    private val logger: HttpLoggingInterceptor
        get() {
            val logging = HttpLoggingInterceptor()
            if(BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            }
            return logging
        }

    init {
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.addInterceptor(accessKeyInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        val client: OkHttpClient = okHttpBuilder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(EXCHANGE_RATE_API_BASE_URL)
            .client(client).addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    private fun getMoshi(): Moshi {
        return Moshi.Builder().add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory()).build()
    }

}