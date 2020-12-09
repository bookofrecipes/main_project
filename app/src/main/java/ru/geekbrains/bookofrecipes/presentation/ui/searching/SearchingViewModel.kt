package ru.geekbrains.bookofrecipes.presentation.ui.searching

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.bookofrecipes.data.response.RecipesByIngredientsResponse
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRecipesByIngredients
import ru.geekbrains.bookofrecipes.presentation.models.RecipeModelForRecycler
import ru.geekbrains.bookofrecipes.presentation.ui.BaseViewModel

class SearchingViewModel (private val getRecipesByIngredients: GetRecipesByIngredients) : BaseViewModel() {

    private val _recipes = MutableLiveData<List<RecipeModelForRecycler>>()
    val recipes: LiveData<List<RecipeModelForRecycler>> = _recipes

    fun loadRecipes(ingredients : String, num : Int) =
            getRecipesByIngredients(GetRecipesByIngredients.IngredientsParams(ingredients,num)) { it.fold(::handleFailure, ::handleRecipes) }

    private fun handleRecipes(recipesByIngredients: RecipesByIngredientsResponse?) {
        _recipes.value = recipesByIngredients?.map { recipeInfo ->
            RecipeModelForRecycler(
                    recipeInfo.imageUrl,
                    recipeInfo.title,
                    recipeInfo.dishSummary
            )
        }
    }
}