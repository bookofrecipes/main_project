package ru.geekbrains.bookofrecipes.data.response

import com.google.gson.annotations.SerializedName
import ru.geekbrains.bookofrecipes.data.local.entity.Ingredient
import ru.geekbrains.bookofrecipes.data.local.entity.Instruction
import ru.geekbrains.bookofrecipes.data.local.entity.Nutrition

data class RecipeInformationResponse(
        @SerializedName("aggregateLikes")
        val likesCount: Int?,
        @SerializedName("analyzedInstructions")
        val instruction: List<Instruction>?,
        val dairyFree: Boolean?,
        @SerializedName("extendedIngredients")
        val ingredientList: List<Ingredient>?,
        val glutenFree: Boolean?,
        val healthScore: Double,
        @SerializedName("id")
        val dishId: Long?,
        @SerializedName("image")
        val dishImageUrl: String?,
        val instructions: String?,
        val readyInMinutes: Int?,
        val spoonacularScore: Double,
        @SerializedName("summary")
        val dishSummary: String,
        @SerializedName("title")
        val dishName: String?,
        val vegetarian: Boolean?,
        val creditText: String?,
        val nutrition: Nutrition?
)




