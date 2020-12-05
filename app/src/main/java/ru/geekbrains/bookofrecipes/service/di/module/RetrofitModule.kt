package ru.geekbrains.bookofrecipes.service.di.module

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.bookofrecipes.BuildConfig
import ru.geekbrains.bookofrecipes.data.network.ApiHelper
import ru.geekbrains.bookofrecipes.data.network.SpoonacularApiService
import java.util.concurrent.TimeUnit

internal const val BASIC_URL = "https://api.spoonacular.com/recipes/"
private const val API_KEY = "379f3a723272484ca6a7ff139408e7a4"

private const val TIMEOUT_IN_SECONDS = 1L

internal fun provideOkHTTPClient(): OkHttpClient {
    val requestInterceptor = Interceptor { chain ->
        val httpUrl = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("apiKey", API_KEY)
            .build()
        val request = chain.request()
            .newBuilder()
            .url(httpUrl)
            .build()
        return@Interceptor chain.proceed(request)
    }
    return if (BuildConfig.DEBUG) {
        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .build()
    } else {
        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .build()
    }
}

internal fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

internal fun provideApiService(retrofit: Retrofit): SpoonacularApiService =
    retrofit.create(SpoonacularApiService::class.java)

val retrofitModule = module {
    single { provideOkHTTPClient() }
    single { provideRetrofit(get(), BASIC_URL) }
    single { provideApiService(get()) }
    single { ApiHelper(get(), get()) }
}