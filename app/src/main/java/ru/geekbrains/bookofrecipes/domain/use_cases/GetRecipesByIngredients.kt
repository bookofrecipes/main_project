package ru.geekbrains.bookofrecipes.domain.use_cases

import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse

class GetRecipesByIngredients(private val recipesRepo: RecipesRepository) :
    UseCase<RecipesByIngredientsResponse, GetRecipesByIngredients.IngredientsParams>() {

    override suspend fun run(params: IngredientsParams): RecipesByIngredientsResponse {
        return recipesRepo.getRecipeByIngredients(params.ingredients, params.quantity)
    }

    data class IngredientsParams(val ingredients: String, val quantity: Int)
}