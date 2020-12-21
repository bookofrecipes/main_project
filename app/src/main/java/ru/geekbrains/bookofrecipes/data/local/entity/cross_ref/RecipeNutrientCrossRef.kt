package ru.geekbrains.bookofrecipes.data.local.entity.cross_ref

import androidx.room.Entity

@Entity(primaryKeys = ["recipeId", "nutrientId"])
data class RecipeNutrientCrossRef(
    val recipeId: Long,
    val nutrientId: Long
)