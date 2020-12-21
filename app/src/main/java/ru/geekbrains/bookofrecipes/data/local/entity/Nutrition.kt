package ru.geekbrains.bookofrecipes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Nutrition(
    val nutrients: List<Nutrient>
) {
    companion object {
        val empty = Nutrition(emptyList())
    }
}

@Entity
data class Nutrient(
    @PrimaryKey(autoGenerate = true)
    val nutrientId: Long,
    val amount: Double?,
    val percentOfDailyNeeds: Double?,
    val title: String,
    val unit: String?
)