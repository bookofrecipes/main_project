package ru.geekbrains.bookofrecipes.domain.use_cases

import ru.geekbrains.bookofrecipes.data.DataSourceType
import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse

class GetRecipeInformation(private val recipesRepo: RecipesRepository) :
    UseCase<RecipeInformationResponse, GetRecipeInformation.RecipeInfoParams>() {

    override suspend fun run(params: RecipeInfoParams): RecipeInformationResponse {
        return recipesRepo.getRecipeInformation(params.id, params.dataSource)
    }

    data class RecipeInfoParams(val id: Long, val dataSource: DataSourceType)

}