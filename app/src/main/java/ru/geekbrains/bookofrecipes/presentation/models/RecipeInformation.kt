package ru.geekbrains.bookofrecipes.presentation.models

import ru.geekbrains.bookofrecipes.data.local.entity.Ingredient
import ru.geekbrains.bookofrecipes.data.local.entity.Instruction
import java.io.Serializable

data class RecipeInformation(
    val id: Long?,
    val name: String?,
    val imageUrl: String?,
    val cookTimeInMinutes: Int?,
    val likes: Int?,
    val summary: String?,
    val intructions: List<Instruction>?,
    val ingrediens: List<Ingredient>?
) : Serializable {
    companion object {
        val empty = RecipeInformation(
            0, "", "", 0,
            0, "", emptyList(), emptyList()
        )
    }
}
