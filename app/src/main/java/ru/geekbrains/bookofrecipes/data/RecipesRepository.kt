package ru.geekbrains.bookofrecipes.data

import ru.geekbrains.bookofrecipes.data.network.ApiHelper
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.domain.Repository
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

class RecipesRepository(private val remoteDataSource: ApiHelper) : Repository {

    override suspend fun getRandomRecipes(quantityOfRandom: Int): Either<Failure, RandomRecipesResponse?> {
        return remoteDataSource.getData(quantityOfRandom)
    }

    override suspend fun getRecipeInformation(
        id: Long,
        sourceType: DataSourceType
    ): Either<Failure, RecipeInformationResponse?> {
        when (sourceType) {
            DataSourceType.LocalDataSource -> TODO()
            DataSourceType.RemoteDataSource -> TODO()
        }
    }

    override suspend fun getRecipeByIngredients(
        ingredients: String,
        quantityOfRecipes: Int
    ): Either<Failure, RecipesByIngredientsResponse?> {
        return remoteDataSource.getData(ingredients,quantityOfRecipes)
    }

    override suspend fun getRecipeInformationBulk(ids: String): Either<Failure, List<RecipeInformationResponse>> =
        remoteDataSource.getBulkRecipeInfo(ids)
}