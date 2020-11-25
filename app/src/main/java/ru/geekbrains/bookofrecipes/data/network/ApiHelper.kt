package ru.geekbrains.bookofrecipes.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import java.util.concurrent.TimeUnit

private const val BASIC_URL = "https://api.spoonacular.com/recipes/"
private const val API_KEY = "379f3a723272484ca6a7ff139408e7a4"


class ApiHelper : DataSource{
    private fun getOkHttpClient(): OkHttpClient {
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
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASIC_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()


    }
    private fun getApiService(): SpoonacularApiService = getRetrofit().create(SpoonacularApiService::class.java)


    override suspend fun getData(quantityOfRandom : Int): Response<RandomRecipesResponse> {
        return getApiService().getRandomRecipes(quantityOfRandom)
    }

    override suspend fun getData(id : Long): Response<RecipeInformationResponse> {
        return getApiService().getRecipeInformation(id)
    }

    override suspend fun getData(ingredients: String, quantityOfRecipes: Int): Response<RecipesByIngredientsResponse> {
        return getApiService().getRecipesByIngredients(ingredients, quantityOfRecipes)
    }

}