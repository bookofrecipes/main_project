package ru.geekbrains.bookofrecipes.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.bookofrecipes.data.local.entity.Ingredient
import ru.geekbrains.bookofrecipes.data.local.entity.Recipe
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeIngredientCrossRef

@Database(entities = [
    Recipe::class,
    Ingredient::class,
    RecipeIngredientCrossRef::class
],
    version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}