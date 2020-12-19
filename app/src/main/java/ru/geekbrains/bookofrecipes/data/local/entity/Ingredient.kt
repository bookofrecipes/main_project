package ru.geekbrains.bookofrecipes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val ingredientId: Long,
    @SerializedName("image")
    val ingredientImageUrl: String?,
    @SerializedName("name")
    val ingredientName: String?,
    @SerializedName("original")
    val ingredientDescription: String?
)

