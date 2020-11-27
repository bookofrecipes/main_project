package ru.geekbrains.bookofrecipes.domain

import ru.geekbrains.bookofrecipes.data.DataSourceType
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

interface Repository {
    suspend fun getRandomRecipes(quantityOfRandom: Int): Either<Failure, RandomRecipesResponse?>

    suspend fun getRecipeInformation(
        id: Long,
        sourceType: DataSourceType
    ): Either<Failure, RecipeInformationResponse?>

    suspend fun getRecipeByIngredients(
        ingredients: String,
        quantityOfRecipes: Int
    ): Either<Failure, RecipesByIngredientsResponse?>
}