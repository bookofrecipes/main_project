package ru.geekbrains.bookofrecipes.data.response

import com.google.gson.annotations.SerializedName

class RecipesByIngredientsResponseItem (
    @SerializedName("id")
    val id: Long?,
    @SerializedName("image")
    val imageUrl: String?,
    @SerializedName("likes")
    val likes: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("summary")
    val dishSummary: String
)
