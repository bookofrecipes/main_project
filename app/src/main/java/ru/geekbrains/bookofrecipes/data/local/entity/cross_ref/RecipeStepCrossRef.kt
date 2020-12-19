package ru.geekbrains.bookofrecipes.data.local.entity.cross_ref

import androidx.room.Entity

@Entity(primaryKeys = ["recipeId", "number"])
data class RecipeStepCrossRef(
    val recipeId: Long,
    val number: Int
)