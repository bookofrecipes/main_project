package ru.geekbrains.bookofrecipes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    Recipe::class,
    Ingredient::class,
    RecipeIngredientCrossRef::class
],
    version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}