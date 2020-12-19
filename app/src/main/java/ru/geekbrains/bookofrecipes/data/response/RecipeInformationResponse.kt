package ru.geekbrains.bookofrecipes.data.response

import com.google.gson.annotations.SerializedName
import ru.geekbrains.bookofrecipes.data.local.entity.Ingredient
import ru.geekbrains.bookofrecipes.data.local.entity.Instruction
import ru.geekbrains.bookofrecipes.data.local.entity.Nutrition

data class RecipeInformationResponse (
    @SerializedName("aggregateLikes") val likesCount: Int?,
    @SerializedName("analyzedInstructions") val instruction: List<Instruction>?,
    @SerializedName("dairyFree") val dairyFree: Boolean?,
    @SerializedName("extendedIngredients") val ingredientList: List<Ingredient>?,
    @SerializedName("glutenFree") val glutenFree: Boolean?,
    @SerializedName("healthScore") val healthScore: Double,
    @SerializedName("id") val dishId: Long?,
    @SerializedName("image") val dishImageUrl: String?,
    @SerializedName("instructions") val instructions: String?,
    @SerializedName("readyInMinutes") val readyInMinutes: Int?,
    @SerializedName("spoonacularScore") val spoonacularScore: Double,
    @SerializedName("summary") val dishSummary: String,
    @SerializedName("title") val dishName: String?,
    @SerializedName("vegetarian") val vegetarian: Boolean?,
    @SerializedName("creditsText") val creditText: String?,
    @SerializedName("nutrition") val nutrition: Nutrition?
    )




