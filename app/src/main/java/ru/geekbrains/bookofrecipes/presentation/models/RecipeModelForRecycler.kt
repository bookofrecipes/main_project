package ru.geekbrains.bookofrecipes.presentation.models

import java.io.Serializable

data class RecipeModelForRecycler(
    val id: Long?,
    val imageUrl: String?,
    val title: String?,
    val summary: String?
) : Serializable

