package ru.geekbrains.bookofrecipes.domain.use_cases

import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse

class GetRandomRecipe(private val recipesRepo: RecipesRepository) :
    UseCase<RandomRecipesResponse, Int>() {

    override suspend fun run(params: Int): RandomRecipesResponse {
        return recipesRepo.getRandomRecipes(params)
    }

}