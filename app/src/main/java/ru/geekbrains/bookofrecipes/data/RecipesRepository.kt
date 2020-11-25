package ru.geekbrains.bookofrecipes.data

import ru.geekbrains.bookofrecipes.data.network.ApiHelper
import ru.geekbrains.bookofrecipes.data.network.DataSource
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.domain.Repository

class RecipesRepository(private val remoteDataSource: DataSource = ApiHelper()) : Repository {

    override suspend fun getRandomRecipes(quantityOfRandom: Int): RandomRecipesResponse {
        return remoteDataSource.getData(quantityOfRandom)
    }

    override suspend fun getRecipeInformation(
        id: Long,
        sourceType: DataSourceType
    ): RecipeInformationResponse {
        when (sourceType) {
            DataSourceType.LocalDataSource -> TODO()
            DataSourceType.RemoteDataSource -> TODO()
        }
    }

    override suspend fun getRecipeByIngredients(
        ingredients: String,
        quantityOfRecipes: Int
    ): RecipesByIngredientsResponse {
        TODO("Not yet implemented")
    }
}