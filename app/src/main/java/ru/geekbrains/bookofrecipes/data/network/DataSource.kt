package ru.geekbrains.bookofrecipes.data.network

import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

interface DataSource {

    suspend fun getData(quantityOfRandom: Int): Either<Failure, RandomRecipesResponse?>

    suspend fun getData(id: Long): Either<Failure, RecipeInformationResponse?>

    suspend fun getData(
        ingredients: String,
        quantityOfRecipes: Int
    ): Either<Failure, RecipesByIngredientsResponse?>

    suspend fun getBulkRecipeInfo(ids: String): Either<Failure, List<RecipeInformationResponse>>
}