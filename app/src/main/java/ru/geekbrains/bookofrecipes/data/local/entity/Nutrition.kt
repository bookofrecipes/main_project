package ru.geekbrains.bookofrecipes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Nutrition(
    @SerializedName("nutrients")
    val nutrients: List<Nutrient>
)

@Entity
data class Nutrient(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("percentOfDailyNeeds")
    val percentOfDailyNeeds: Double?,
    @SerializedName("title")
    @PrimaryKey(autoGenerate = false)
    val title: String,
    @SerializedName("unit")
    val unit: String?
)