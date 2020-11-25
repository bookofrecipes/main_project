package ru.geekbrains.bookofrecipes.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse

interface SpoonacularApiService {

    @GET("random")
    suspend fun getRandomRecipes(
            @Query("number") resultLimit: Int
    ): Response<RandomRecipesResponse>

    @GET("{id}/information")
    suspend fun getRecipeInformation(
            @Path("id") id: Long): Response<RecipeInformationResponse>

    @GET("findByIngredients")
    suspend fun getRecipesByIngredients(
            @Query("ingredients") ingredients: String,
            @Query("number") resultLimit: Int
    ): Response<RecipesByIngredientsResponse>
}