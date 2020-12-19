package ru.geekbrains.bookofrecipes.data.local.entity

import com.google.gson.annotations.SerializedName

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