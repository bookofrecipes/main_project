package ru.geekbrains.bookofrecipes.domain.use_cases

import ru.geekbrains.bookofrecipes.domain.Repository
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

class SaveRecipeToFavorites(private val repository: Repository) :
    UseCase<Long, RecipeInformation>() {

    override suspend fun run(params: RecipeInformation): Either<Failure, Long> =
        repository.saveFavoriteRecipe(params)
}