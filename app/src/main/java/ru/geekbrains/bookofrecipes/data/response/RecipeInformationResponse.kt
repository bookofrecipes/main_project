package ru.geekbrains.bookofrecipes.data.response

import com.google.gson.annotations.SerializedName

data class RecipeInformationResponse (
        @SerializedName("aggregateLikes")
        val likesCount: Int?,
        @SerializedName("analyzedInstructions")
        val instruction: List<Instruction>?,
        @SerializedName("dairyFree")
        val dairyFree: Boolean?,
        @SerializedName("extendedIngredients")
        val ingredientList: List<Ingredient>?,
        @SerializedName("glutenFree")
        val glutenFree: Boolean?,
        @SerializedName("healthScore")
        val healthScore: Double,
        @SerializedName("id")
        val dishId: Long?,
        @SerializedName("image")
        val dishImageUrl: String?,
        @SerializedName("instructions")
        val instructions: String?,
        @SerializedName("readyInMinutes")
        val readyInMinutes: Int?,
        @SerializedName("spoonacularScore")
        val spoonacularScore: Double,
        @SerializedName("summary")
        val dishSummary: String,
        @SerializedName("title")
        val dishName: String?,
        @SerializedName("vegetarian")
        val vegetarian: Boolean?,
        @SerializedName("creditsText")
        val creditText: String?,
        @SerializedName("nutrition")
        val nutrition: Nutrition?
    )

    data class Ingredient(
        @SerializedName("id")
        val ingredientId: Long,
        @SerializedName("image")
        val ingredientImageUrl: String?,
        @SerializedName("name")
        val ingredientName: String?,
        @SerializedName("original")
        val description: String?
    )

    data class Instruction(
        @SerializedName("steps")
        val stepList: List<Step>
    )

    data class Step(
        @SerializedName("number")
        val number: Int,
        @SerializedName("step")
        val step: String
    )

    data class Nutrition(
        @SerializedName("nutrients")
        val nutrients: List<Nutrient>
    )

    data class Nutrient(
        @SerializedName("amount")
        val amount: Double?,
        @SerializedName("percentOfDailyNeeds")
        val percentOfDailyNeeds: Double?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("unit")
        val unit: String?
    )
