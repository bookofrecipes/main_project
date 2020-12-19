package ru.geekbrains.bookofrecipes.data.local.db

import androidx.room.*
import ru.geekbrains.bookofrecipes.data.local.entity.Ingredient
import ru.geekbrains.bookofrecipes.data.local.entity.Recipe
import ru.geekbrains.bookofrecipes.data.local.entity.Step
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeIngredientCrossRef
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeStepCrossRef
import ru.geekbrains.bookofrecipes.data.local.entity.relations.RecipeWithIngredients
import ru.geekbrains.bookofrecipes.data.local.entity.relations.RecipeWithSteps

@Dao
interface RecipeDao {
    //insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: Ingredient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStep(step: Step)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredientCrossRef (ingredientCrossRef: RecipeIngredientCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeStepCrossRef (stepCrossRef: RecipeStepCrossRef)
    //clear
    @Query("DELETE FROM recipe")
    suspend fun clearRecipeDB()

    @Query("DELETE FROM ingredient")
    suspend fun clearIngredientDB()

//    @Query("DELETE FROM recipeingredientcrossref")
//    suspend fun clearRecipeIngredientCrossRef()
//    //delete
//    @Query("DELETE FROM recipe WHERE recipeId = :id")
//    suspend fun deleteRecipe(id : Long) : Long
//
//    @Query("DELETE FROM recipe WHERE recipeId = :id")
//    suspend fun deleteIngredient(id : Long) : Long

    @Transaction
    @Query("SELECT * FROM recipe WHERE recipeId = :id")
    suspend fun getStepsOfRecipe(id: Long): List<RecipeWithSteps>

    @Transaction
    @Query("SELECT * FROM recipe WHERE recipeId = :id")
    suspend fun getIngredientsOfRecipe(id: Long): List<RecipeWithIngredients>



}
