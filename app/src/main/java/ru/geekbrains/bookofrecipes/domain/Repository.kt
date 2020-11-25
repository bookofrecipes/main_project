package ru.geekbrains.bookofrecipes.domain

import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse

interface Repository {
    suspend fun getRandomRecipes(quantityOfRandom: Int): RandomRecipesResponse

    suspend fun getRecipeInformation(id: Long): RecipeInformationResponse

    suspend fun getRecipeByIngredients(
        ingredients: String,
        quantityOfRecipes: Int
    ): RecipesByIngredientsResponse
}