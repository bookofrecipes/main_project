package ru.geekbrains.bookofrecipes.data.local.entity.cross_ref

import androidx.room.Entity

@Entity(primaryKeys = ["recipeId", "title"])
data class RecipeNutrientCrossRef(
    val recipeId: Long,
    val title: String
)