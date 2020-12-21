package ru.geekbrains.bookofrecipes.data.local.entity.cross_ref

import androidx.room.Entity

@Entity(primaryKeys = ["recipeId", "stepId"])
data class RecipeStepCrossRef(
    val recipeId: Long,
    val stepId: Long
)