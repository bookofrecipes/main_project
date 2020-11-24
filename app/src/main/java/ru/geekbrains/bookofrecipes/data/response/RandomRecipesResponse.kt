package ru.geekbrains.bookofrecipes.data.response

import com.google.gson.annotations.SerializedName

class RandomRecipesResponse (
    @SerializedName("recipes")
    val recipes: List<RecipeInformationResponse>
)