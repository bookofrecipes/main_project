package ru.geekbrains.bookofrecipes.data.local.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.geekbrains.bookofrecipes.data.local.entity.Recipe
import ru.geekbrains.bookofrecipes.data.local.entity.Step
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeStepCrossRef

data class RecipeWithSteps(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "stepId",
        associateBy = Junction(RecipeStepCrossRef::class)
    )
    val steps : List<Step>
)