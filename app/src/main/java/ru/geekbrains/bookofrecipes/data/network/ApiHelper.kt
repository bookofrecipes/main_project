package ru.geekbrains.bookofrecipes.data.network

import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse

class ApiHelper(private val apiService: SpoonacularApiService) : DataSource {

    override suspend fun getData(quantityOfRandom: Int): RandomRecipesResponse {
        val response = apiService.getRandomRecipes(quantityOfRandom)
        return response.body()!!
    }

    override suspend fun getData(id: Long): RecipeInformationResponse {
        return apiService.getRecipeInformation(id).body()!!
    }

    override suspend fun getData(
        ingredients: String,
        quantityOfRecipes: Int
    ): RecipesByIngredientsResponse {
        return apiService.getRecipesByIngredients(ingredients, quantityOfRecipes).body()!!
    }

}