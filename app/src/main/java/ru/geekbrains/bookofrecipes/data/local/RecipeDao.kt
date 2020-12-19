package ru.geekbrains.bookofrecipes.data.local

import androidx.room.*

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: Ingredient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredientCrossRef (crossRef: RecipeIngredientCrossRef)

    @Query("DELETE FROM recipe")
    suspend fun clearRecipeDB()

    @Query("DELETE FROM ingredient")
    suspend fun clearIngredientDB()

    @Transaction
    @Query("SELECT * FROM recipe WHERE recipeId = :id")
    suspend fun getIngredientsOfRecipe(id: Long): List<RecipeWithIngredients>



}
