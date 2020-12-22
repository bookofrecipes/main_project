package ru.geekbrains.bookofrecipes.data.local.entity

import androidx.room.*

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = false)
    val recipeId: Long,
    val recipeImageUrl: String?,
    val readyInMinutes: Int?,
    val recipeSummary: String?,
    val recipeName: String?,
    val likesCount: Int?
)


