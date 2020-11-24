package ru.geekbrains.bookofrecipes.data.network

import retrofit2.Response
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse

interface DataSource {
    suspend fun getData(quantityOfRandom : Int): Response<RandomRecipesResponse>
    suspend fun getData(id : Long): Response<RecipeInformationResponse>
    suspend fun getData(ingredients: String, quantityOfRecipes : Int): Response<RecipesByIngredientsResponse>
}