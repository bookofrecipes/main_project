package ru.geekbrains.bookofrecipes.domain.use_cases

import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

class GetRecipesByIngredients(private val recipesRepo: RecipesRepository) :
    UseCase<RecipesByIngredientsResponse?, GetRecipesByIngredients.IngredientsParams>() {

    override suspend fun run(params: IngredientsParams): Either<Failure, RecipesByIngredientsResponse?> {
        return recipesRepo.getRecipeByIngredients(params.ingredients, params.quantity)
    }

    data class IngredientsParams(val ingredients: String, val quantity: Int)
}