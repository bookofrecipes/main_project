package ru.geekbrains.bookofrecipes.data.network

import retrofit2.Response
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either
import ru.geekbrains.bookofrecipes.service.functional.Either.Left
import ru.geekbrains.bookofrecipes.service.functional.Either.Right

class ApiHelper(private val apiService: SpoonacularApiService) : DataSource {

    override suspend fun getData(quantityOfRandom: Int): Either<Failure, RandomRecipesResponse?> =
        responseHandle(apiService.getRandomRecipes(quantityOfRandom))

    override suspend fun getData(id: Long): Either<Failure, RecipeInformationResponse?> =
        responseHandle(apiService.getRecipeInformation(id))

    override suspend fun getData(
        ingredients: String,
        quantityOfRecipes: Int
    ): Either<Failure, RecipesByIngredientsResponse?> =
        responseHandle(apiService.getRecipesByIngredients(ingredients, quantityOfRecipes))

    private fun <T> responseHandle(response: Response<T>): Either<Failure, T?> =
        when (response.isSuccessful) {
            true -> Right(response.body())
            false -> Left(Failure.ServerError)
        }
}