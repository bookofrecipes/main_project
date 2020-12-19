package ru.geekbrains.bookofrecipes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Instruction(
    @SerializedName("steps")
    val stepList: List<Step>
)

@Entity
data class Step(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("number")
    val number: Int,
    @SerializedName("step")
    val step: String
)