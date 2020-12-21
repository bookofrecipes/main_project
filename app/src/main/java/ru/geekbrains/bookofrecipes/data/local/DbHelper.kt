package ru.geekbrains.bookofrecipes.data.local

import ru.geekbrains.bookofrecipes.data.local.db.RecipeDao
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeIngredientCrossRef
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeStepCrossRef
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

class DbHelper(private val recipeDao: RecipeDao) : LocalDataSource {

    override fun getRecipeList(): Either<Failure, RecipeInformation> {
        TODO("Not yet implemented")
    }

    override suspend fun saveRecipe(recipe: RecipeInformation): Either<Failure, Long> {
        val id = recipeDao.insertRecipe(recipe.toEntity())

        recipe.ingredients?.forEach { ingredient ->
            recipeDao.insertIngredient(ingredient)
            recipeDao.insertRecipeIngredientCrossRef(
                RecipeIngredientCrossRef(recipe.id, ingredient.ingredientId)
            )
        }

        recipe.instructions?.forEach {
            it.stepList.forEach { step ->
                val stepId = recipeDao.insertStep(step)
                recipeDao.insertRecipeStepCrossRef(RecipeStepCrossRef(recipe.id, stepId))
            }
        }
        return Either.Right(id)
    }
}