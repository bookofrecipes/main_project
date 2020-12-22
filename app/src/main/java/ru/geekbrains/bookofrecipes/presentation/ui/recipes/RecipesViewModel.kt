package ru.geekbrains.bookofrecipes.presentation.ui.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipeInformationResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRandomRecipes
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRecipeInformationBulk
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRecipesByIngredients
import ru.geekbrains.bookofrecipes.domain.use_cases.SaveRecipeToFavorites
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.presentation.ui.BaseViewModel

class RecipesViewModel(
    private val getRandomRecipes: GetRandomRecipes,
    private val getRecipesByIngredients: GetRecipesByIngredients,
    private val getRecipesRecipeInformationBulk: GetRecipeInformationBulk,
    private val saveRecipeToFavorites: SaveRecipeToFavorites
) : BaseViewModel() {

    private val _recipesInfo = MutableLiveData<List<RecipeInformation>>()
    val recipesInfo: LiveData<List<RecipeInformation>> = _recipesInfo

    var lastRequestMade: () -> Unit = {}

    fun loadRandomRecipes() {
        lastRequestMade = { loadRandomRecipes() }
        getRandomRecipes(10) { it.fold(::handleFailure, ::handleRandomRecipes) }
    }

    fun loadRecipesByIngredients(ingredients: String) {
        lastRequestMade = { loadRecipesByIngredients(ingredients) }
        getRecipesByIngredients(GetRecipesByIngredients.IngredientsParams(ingredients, 10)) {
            it.fold(::handleFailure, ::handleRecipesByIngredients)
        }
    }

    private fun loadRecipeInfoByIds(ids: String) {
        lastRequestMade = { loadRecipeInfoByIds(ids) }
        getRecipesRecipeInformationBulk(ids) {
            it.fold(::handleFailure, ::handleRecipeInformationBulk)
        }
    }

    private fun handleRandomRecipes(randomRecipes: RandomRecipesResponse?) {
        _recipesInfo.value = randomRecipes?.recipes?.map { recipeInfo ->
            RecipeInformation(
                recipeInfo.dishId,
                recipeInfo.dishName,
                recipeInfo.dishImageUrl,
                recipeInfo.readyInMinutes,
                recipeInfo.likesCount,
                recipeInfo.dishSummary,
                recipeInfo.instruction,
                recipeInfo.ingredientList
            )
        }
    }

    private fun handleRecipesByIngredients(recipesByIngredients: RecipesByIngredientsResponse?) {
        var ids = ""

        recipesByIngredients?.forEach { recipeInfo ->
            ids += (recipeInfo.id.toString() + ",")
        }

        if (ids.isNotBlank())
            loadRecipeInfoByIds(ids)
    }

    private fun handleRecipeInformationBulk(recipeInformationBulk: List<RecipeInformationResponse>) {
        _recipesInfo.value = recipeInformationBulk.map { recipesInfo ->
            RecipeInformation(
                recipesInfo.dishId,
                recipesInfo.dishName,
                recipesInfo.dishImageUrl,
                recipesInfo.readyInMinutes,
                recipesInfo.likesCount,
                recipesInfo.dishSummary,
                recipesInfo.instruction,
                recipesInfo.ingredientList
            )
        }
    }

    fun addToFavourites(recipe: RecipeInformation) =
        saveRecipeToFavorites(recipe) { it.fold(::handleFailure, ::handleFavoritesAddition) }

    private fun handleFavoritesAddition(id: Long) {
        Log.d("Room id: ", id.toString())
    }
}