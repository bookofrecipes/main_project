package ru.geekbrains.bookofrecipes.domain.use_cases

import ru.geekbrains.bookofrecipes.domain.Repository
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

class GetFavoritesRecipes(private val repository: Repository) :
    UseCase<List<RecipeInformation>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<RecipeInformation>> =
        repository.getFavouriteRecipes()

}