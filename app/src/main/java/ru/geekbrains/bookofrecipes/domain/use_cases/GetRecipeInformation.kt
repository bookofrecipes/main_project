package ru.geekbrains.bookofrecipes.domain.use_cases

import ru.geekbrains.bookofrecipes.data.DataSourceType
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.domain.Repository
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

class GetRecipeInformation(private val recipesRepo: Repository) :
    UseCase<RecipeInformationResponse?, GetRecipeInformation.RecipeInfoParams>() {

    override suspend fun run(params: RecipeInfoParams): Either<Failure, RecipeInformationResponse?> {
        return recipesRepo.getRecipeInformation(params.id, params.dataSource)
    }

    data class RecipeInfoParams(val id: Long, val dataSource: DataSourceType)

}