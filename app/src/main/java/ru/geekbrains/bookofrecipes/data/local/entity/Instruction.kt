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
    @PrimaryKey(autoGenerate = true)
    val stepId : Long,
    val number: Int,
    val step: String
)