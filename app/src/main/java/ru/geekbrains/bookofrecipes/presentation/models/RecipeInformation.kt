package ru.geekbrains.bookofrecipes.presentation.models

import ru.geekbrains.bookofrecipes.data.local.entity.Ingredient
import ru.geekbrains.bookofrecipes.data.local.entity.Instruction
import ru.geekbrains.bookofrecipes.data.local.entity.Recipe
import ru.geekbrains.bookofrecipes.data.local.entity.relations.RecipeWithIngredients
import java.io.Serializable

data class RecipeInformation(
    val id: Long,
    val name: String?,
    val imageUrl: String?,
    val cookTimeInMinutes: Int?,
    val likes: Int?,
    val summary: String?,
    val instructions: List<Instruction>?,
    val ingredients: List<Ingredient>?
) : Serializable {

    constructor(recipeEntity: RecipeWithIngredients) : this(
        recipeEntity.recipe.recipeId,
        recipeEntity.recipe.recipeName,
        recipeEntity.recipe.recipeImageUrl,
        recipeEntity.recipe.readyInMinutes,
        recipeEntity.recipe.likesCount,
        recipeEntity.recipe.recipeSummary, emptyList(),
        recipeEntity.ingredients
    )

    fun toEntity(): Recipe =
        Recipe(id, imageUrl, cookTimeInMinutes, summary, name, likes)

    companion object {
        val empty = RecipeInformation(
            0, "", "", 0,
            0, "", emptyList(), emptyList()
        )
    }
}
