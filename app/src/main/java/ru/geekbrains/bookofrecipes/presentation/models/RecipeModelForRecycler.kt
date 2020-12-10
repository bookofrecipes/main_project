package ru.geekbrains.bookofrecipes.presentation.models

import org.w3c.dom.Text
import ru.geekbrains.bookofrecipes.data.response.Instruction
import java.io.Serializable

data class RecipeModelForRecycler(
    val id: Long?,
    val imageUrl: String?,
    val title: String?,
    val summary: String?,
    val creditText: String?
) : Serializable

