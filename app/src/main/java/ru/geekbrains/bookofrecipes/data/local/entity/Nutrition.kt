package ru.geekbrains.bookofrecipes.data.local.entity

import com.google.gson.annotations.SerializedName

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