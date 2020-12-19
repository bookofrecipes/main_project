package ru.geekbrains.bookofrecipes.data.local.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.geekbrains.bookofrecipes.data.local.entity.Nutrient
import ru.geekbrains.bookofrecipes.data.local.entity.Recipe
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeNutrientCrossRef

data class RecipeWithNutrients(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "title",
        associateBy = Junction(RecipeNutrientCrossRef::class)
    )
    val nutrients : List<Nutrient>
)