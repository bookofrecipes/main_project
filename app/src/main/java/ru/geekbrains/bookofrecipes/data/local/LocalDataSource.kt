package ru.geekbrains.bookofrecipes.data.local

import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

interface LocalDataSource {
    fun getRecipeList(): Either<Failure, RecipeInformation>

    suspend fun saveRecipe(recipe: RecipeInformation): Either<Failure, Long>
}