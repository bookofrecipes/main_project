package ru.geekbrains.bookofrecipes.service.di.module

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.bookofrecipes.BuildConfig
import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.data.network.ApiHelper
import ru.geekbrains.bookofrecipes.data.network.SpoonacularApiService
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRandomRecipes
import ru.geekbrains.bookofrecipes.presentation.ui.recipes.RecipesViewModel
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter
import java.util.concurrent.TimeUnit

private const val BASIC_URL = "https://api.spoonacular.com/recipes/"
private const val API_KEY = "379f3a723272484ca6a7ff139408e7a4"

private fun provideOkHTTPClient(): OkHttpClient {
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
    if (BuildConfig.DEBUG) {
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    } else {
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

private fun provideApiService(retrofit: Retrofit): SpoonacularApiService =
    retrofit.create(SpoonacularApiService::class.java)


val appModule = module {
    single { provideOkHTTPClient() }
    single { provideRetrofit(get(), BASIC_URL) }
    single { provideApiService(get()) }
    single { ApiHelper(get()) }

    single { RecipesAdapter() }
}

val repoModule = module {
    viewModel { RecipesViewModel(get()) }
    single { GetRandomRecipes(get()) }
    single { RecipesRepository(get()) }
}