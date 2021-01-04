package ru.geekbrains.bookofrecipes.data

import ru.geekbrains.bookofrecipes.data.local.LocalDataSource
import ru.geekbrains.bookofrecipes.data.network.RemoteDataSource
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.domain.Repository
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

class RecipesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

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
        return remoteDataSource.getData(ingredients, quantityOfRecipes)
    }

    override suspend fun getRecipeInformationBulk(ids: String): Either<Failure, List<RecipeInformationResponse>> =
            remoteDataSource.getBulkRecipeInfo(ids)

    override suspend fun saveFavoriteRecipe(recipe: RecipeInformation): Either<Failure, Long> =
            localDataSource.saveRecipe(recipe)

    override suspend fun getFavouriteRecipes(): Either<Failure, List<RecipeInformation>> =
            localDataSource.getRecipeList()


    override suspend fun deleteRecipeFromFavorites(recipe: RecipeInformation): Either<Failure, Int> =
        localDataSource.deleteRecipe(recipe)

}