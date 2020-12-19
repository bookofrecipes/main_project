package ru.geekbrains.bookofrecipes.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.bookofrecipes.data.local.entity.Ingredient
import ru.geekbrains.bookofrecipes.data.local.entity.Recipe
import ru.geekbrains.bookofrecipes.data.local.entity.Step
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeIngredientCrossRef
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeStepCrossRef

@Database(entities = [
    Recipe::class,
    Ingredient::class,
    Step::class,
    RecipeIngredientCrossRef::class,
    RecipeStepCrossRef::class
],
    version = 2)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}