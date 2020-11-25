package ru.geekbrains.bookofrecipes.domain.use_cases

import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse

class GetRecipeInformation(private val recipesRepo: RecipesRepository) :
    UseCase<RecipeInformationResponse, Long>() {

    override suspend fun run(params: Long): RecipeInformationResponse {
        return recipesRepo.getRecipeInformation(params)
    }

}