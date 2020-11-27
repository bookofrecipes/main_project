package ru.geekbrains.bookofrecipes.domain.use_cases

import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

class GetRandomRecipes(private val recipesRepo: RecipesRepository) :
    UseCase<RandomRecipesResponse?, Int>() {

    override suspend fun run(params: Int): Either<Failure, RandomRecipesResponse?> {
        return recipesRepo.getRandomRecipes(params)
    }

}