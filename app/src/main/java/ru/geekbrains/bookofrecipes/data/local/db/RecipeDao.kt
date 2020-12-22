package ru.geekbrains.bookofrecipes.data.local.db

import androidx.room.*
import ru.geekbrains.bookofrecipes.data.local.entity.Ingredient
import ru.geekbrains.bookofrecipes.data.local.entity.Nutrient
import ru.geekbrains.bookofrecipes.data.local.entity.Recipe
import ru.geekbrains.bookofrecipes.data.local.entity.Step
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeIngredientCrossRef
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeNutrientCrossRef
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeStepCrossRef
import ru.geekbrains.bookofrecipes.data.local.entity.relations.RecipeWithIngredients
import ru.geekbrains.bookofrecipes.data.local.entity.relations.RecipeWithNutrients
import ru.geekbrains.bookofrecipes.data.local.entity.relations.RecipeWithSteps

@Dao
interface RecipeDao {
    //insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: Ingredient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStep(step: Step): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNutrient(nutrient: Nutrient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredientCrossRef(ingredientCrossRef: RecipeIngredientCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeStepCrossRef(stepCrossRef: RecipeStepCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeNutrientCrossRef(nutrientCrossRef: RecipeNutrientCrossRef)

    //clear
    @Query("DELETE FROM recipe")
    suspend fun clearRecipeDB()

    @Query("DELETE FROM ingredient")
    suspend fun clearIngredientDB()

    @Query("DELETE FROM step")
    suspend fun clearStepDB()

    @Query("DELETE FROM nutrient")
    suspend fun clearNutrientDB()

    //delete by id
    @Query("DELETE FROM ingredient WHERE ingredientId = :id")
    suspend fun deleteIngredient(id: Long)

    @Query("DELETE FROM recipe WHERE recipeId = :id")
    suspend fun deleteRecipe(id: Long)

    @Query("DELETE FROM nutrient WHERE nutrientId = :id")
    suspend fun deleteNutrient(id: Long)

    @Query("DELETE FROM step WHERE stepId = :id")
    suspend fun deleteStep(id: Long)

    //// get relations
    @Transaction
    @Query("SELECT * FROM recipe WHERE recipeId = :id")
    suspend fun getStepsOfRecipe(id: Long): List<RecipeWithSteps>

    @Transaction
    @Query("SELECT * FROM recipe WHERE recipeId = :id")
    suspend fun getIngredientsOfRecipe(id: Long): List<RecipeWithIngredients>

    @Transaction
    @Query("SELECT * FROM recipe WHERE recipeId = :id")
    suspend fun getNutrientsOfRecipe(id: Long): List<RecipeWithNutrients>

    @Transaction
    @Query("SELECT * FROM recipe")
    suspend fun getAllRecipesWithIngredients(): List<RecipeWithIngredients>

    @Transaction
    @Query("SELECT * FROM recipe")
    suspend fun getAllRecipesWithSteps(): List<RecipeWithIngredients>
}
