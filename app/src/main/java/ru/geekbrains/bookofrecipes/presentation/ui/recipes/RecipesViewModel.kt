package ru.geekbrains.bookofrecipes.presentation.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRandomRecipes
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRecipesByIngredients
import ru.geekbrains.bookofrecipes.presentation.models.RecipeModelForRecycler
import ru.geekbrains.bookofrecipes.presentation.ui.BaseViewModel

class RecipesViewModel(
    private val getRandomRecipes: GetRandomRecipes,
    private val getRecipesByIngredients: GetRecipesByIngredients
) : BaseViewModel() {

    private val _recipes = MutableLiveData<List<RecipeModelForRecycler>>()
    val recipes: LiveData<List<RecipeModelForRecycler>> = _recipes

    fun loadRandomRecipes() =
        getRandomRecipes(10) { it.fold(::handleFailure, ::handleRandomRecipes) }

    fun loadRecipesByIngredients(ingredients: String) =
        getRecipesByIngredients(GetRecipesByIngredients.IngredientsParams(ingredients, 10)) {
            it.fold(::handleFailure, ::handleRecipesByIngredients)
        }

    private fun handleRandomRecipes(randomRecipes: RandomRecipesResponse?) {
        _recipes.value = randomRecipes?.recipes?.map { recipeInfo ->
            RecipeModelForRecycler(
                recipeInfo.dishImageUrl,
                recipeInfo.dishName,
                recipeInfo.dishSummary
            )
        }
    }

    private fun handleRecipesByIngredients(recipesByIngredients: RecipesByIngredientsResponse?) {
        _recipes.value = recipesByIngredients?.map { recipeInfo ->
            RecipeModelForRecycler(
                recipeInfo.imageUrl,
                recipeInfo.title,
                recipeInfo.dishSummary
            )
        }
    }
}