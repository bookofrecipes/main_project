package ru.geekbrains.bookofrecipes.domain

import ru.geekbrains.bookofrecipes.data.DataSourceType
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
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

    suspend fun getRecipeInformationBulk(ids: String): Either<Failure, List<RecipeInformationResponse>>

    suspend fun saveFavoriteRecipe(recipe: RecipeInformation): Either<Failure, Long>

    suspend fun getFavouriteRecipes(): Either<Failure, List<RecipeInformation>>

    suspend fun deleteRecipeFromFavorites(recipe: RecipeInformation): Either<Failure, Int>
}