package ru.geekbrains.bookofrecipes.data.local

import androidx.room.*


@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = false)
    val recipeId: Long,
    val recipeImageUrl: String?,
    val readyInMinutes: Int?,
    val recipeSummary: String,
    val recipeName: String?,
)








//foreignKeys = arrayOf(
//ForeignKey(
//entity = Recipe::class,
//parentColumns = arrayOf("id"),
//childColumns = arrayOf("recipeId")
//),
//ForeignKey(
//entity = Ingredient::class,
//parentColumns = arrayOf("id"),
//childColumns = arrayOf("ingredientId")
//)
//))