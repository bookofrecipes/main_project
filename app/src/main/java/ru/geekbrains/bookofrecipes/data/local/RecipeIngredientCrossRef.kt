package ru.geekbrains.bookofrecipes.data.local

import androidx.room.Entity

@Entity(primaryKeys = ["recipeId", "ingredientId"])
data class RecipeIngredientCrossRef(
    val recipeId: Long,
    val ingredientId: Long
)