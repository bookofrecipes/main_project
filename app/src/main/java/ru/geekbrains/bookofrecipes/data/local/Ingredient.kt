package ru.geekbrains.bookofrecipes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = false)
    val ingredientId: Long,
    val ingredientImageUrl: String?,
    val ingredientName: String?,
    val ingredientDescription: String?
)