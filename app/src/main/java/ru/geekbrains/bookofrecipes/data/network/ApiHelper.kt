package ru.geekbrains.bookofrecipes.data.network

import retrofit2.Response
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.Failure.NetworkConnection
import ru.geekbrains.bookofrecipes.service.Failure.ServerError
import ru.geekbrains.bookofrecipes.service.functional.Either
import ru.geekbrains.bookofrecipes.service.functional.Either.Left
import ru.geekbrains.bookofrecipes.service.functional.Either.Right
import ru.geekbrains.bookofrecipes.service.utils.NetworkAvailabilityHandler

class ApiHelper(
    private val apiService: SpoonacularApiService,
    private val networkHandler: NetworkAvailabilityHandler
) : RemoteDataSource {

    override suspend fun getData(quantityOfRandom: Int): Either<Failure, RandomRecipesResponse?> =
        requestData(RandomRecipesResponse.empty) { apiService.getRandomRecipes(quantityOfRandom) }

    override suspend fun getData(id: Long): Either<Failure, RecipeInformationResponse?> =
        requestData(RecipeInformationResponse.empty) { apiService.getRecipeInformation(id) }

    override suspend fun getData(
        ingredients: String,
        quantityOfRecipes: Int
    ): Either<Failure, RecipesByIngredientsResponse?> =
        requestData(RecipesByIngredientsResponse.empty) {
            apiService.getRecipesByIngredients(
                ingredients,
                quantityOfRecipes
            )
        }

    override suspend fun getBulkRecipeInfo(ids: String): Either<Failure, List<RecipeInformationResponse>> =
        requestData(emptyList()) { apiService.getRecipesInformationBulk(ids) }


    private fun <T> responseHandle(response: Response<T>, default: T): Either<Failure, T> =
        when (response.isSuccessful) {
            true -> Right(response.body() ?: default)
            false -> Left(ServerError)
        }


    private suspend fun <T> requestData(default: T, request: suspend () -> Response<T>) =
        when (networkHandler.isConnected()) {
            true -> {
                try {
                    responseHandle(request(), default)
                } catch (exception: Throwable) {
                    Left(ServerError)
                }
            }
            false -> Left(NetworkConnection)
        }
}