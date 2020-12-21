package ru.geekbrains.bookofrecipes.presentation.models

import ru.geekbrains.bookofrecipes.data.local.entity.Ingredient
import ru.geekbrains.bookofrecipes.data.local.entity.Instruction
import ru.geekbrains.bookofrecipes.data.local.entity.Recipe
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

    fun toEntity(): Recipe =
        Recipe(id, imageUrl, cookTimeInMinutes, summary, name, likes)

    companion object {
        val empty = RecipeInformation(
            0, "", "", 0,
            0, "", emptyList(), emptyList()
        )
    }
}
