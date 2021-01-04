package ru.geekbrains.bookofrecipes.data.local

import ru.geekbrains.bookofrecipes.data.local.db.RecipeDao
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeIngredientCrossRef
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeStepCrossRef
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either
import ru.geekbrains.bookofrecipes.service.functional.Either.Right
import kotlin.collections.ArrayList

class DbHelper(private val recipeDao: RecipeDao) : LocalDataSource {

    override suspend fun getRecipeList(): Either<Failure, List<RecipeInformation>> {
        val favoritesRecipes: ArrayList<RecipeInformation> = ArrayList()

        recipeDao.getAllRecipesWithIngredients().forEach { recipe ->
            favoritesRecipes.add(RecipeInformation(recipe))
        }

        return Right(favoritesRecipes)
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
        return Right(id)
    }

    override suspend fun deleteRecipe(recipe: RecipeInformation): Either<Failure, Int> {
        val id = recipeDao.deleteRecipe(recipe.id)
//        recipe.ingredients?.forEach { ingredient ->
//            recipeDao.deleteIngredient(ingredient.ingredientId)
            recipeDao.deleteRecipeIngredientCrossRef(recipe.id)
//        }
        return Right(id)
    }
}