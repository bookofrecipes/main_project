package ru.geekbrains.bookofrecipes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Nutrition(
    val nutrients: List<Nutrient>
)

@Entity
data class Nutrient(
        @PrimaryKey(autoGenerate = true)
        val nutrientId: Long,
        val amount: Double?,
        val percentOfDailyNeeds: Double?,
        val title: String,
        val unit: String?
)