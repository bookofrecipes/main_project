package ru.geekbrains.bookofrecipes.data

import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.domain.Repository

class RecipesRepository : Repository {

    override suspend fun getRandomRecipes(quantityOfRandom: Int): RandomRecipesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeInformation(id: Long): RecipeInformationResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeByIngredients(
        ingredients: String,
        quantityOfRecipes: Int
    ): RecipesByIngredientsResponse {
        TODO("Not yet implemented")
    }
}