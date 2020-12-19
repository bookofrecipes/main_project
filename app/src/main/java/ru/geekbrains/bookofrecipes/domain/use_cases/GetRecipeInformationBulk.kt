package ru.geekbrains.bookofrecipes.domain.use_cases

import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.domain.Repository
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

class GetRecipeInformationBulk(private val repository: Repository) :
    UseCase<List<RecipeInformationResponse>, String>() {

    override suspend fun run(params: String): Either<Failure, List<RecipeInformationResponse>> =
        repository.getRecipeInformationBulk(params)

}