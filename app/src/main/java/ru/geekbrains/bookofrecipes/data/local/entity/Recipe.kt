package ru.geekbrains.bookofrecipes.data.local.entity

import androidx.room.*
import com.google.gson.annotations.SerializedName


@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = false)
    val recipeId: Long,
    val recipeImageUrl: String?,
    val readyInMinutes: Int?,
    val recipeSummary: String?,
    val recipeName: String?,
    val glutenFree: Boolean?,
    val healthScore: Double?,
    val instructions: String?,
    val vegetarian: Boolean?,
    val creditText: String?
)


