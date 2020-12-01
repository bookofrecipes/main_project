package ru.geekbrains.bookofrecipes.presentation.ui.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.bookofrecipes.data.response.RandomRecipesResponse
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRandomRecipes
import ru.geekbrains.bookofrecipes.presentation.models.RecipeModelForRecycler
import ru.geekbrains.bookofrecipes.service.Failure

class RecipesViewModel(private val getRandomRecipes: GetRandomRecipes) : ViewModel() {

    private val _recipes = MutableLiveData<List<RecipeModelForRecycler>>()
    val recipes: LiveData<List<RecipeModelForRecycler>> = _recipes

    fun loadRandomRecipes() =
        getRandomRecipes(10) { it.fold(::handleFailure, ::handleRandomRecipes) }

    private fun handleRandomRecipes(randomRecipes: RandomRecipesResponse?) {
        _recipes.value = randomRecipes?.recipes?.map { recipeInfo ->
            RecipeModelForRecycler(
                recipeInfo.dishImageUrl,
                recipeInfo.dishName,
                recipeInfo.dishSummary
            )
        }
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            Failure.NetworkConnection -> Log.d("Network error", "Request failure")
            Failure.ServerError -> Log.d("Server error", "Request failure")
            else -> return
        }
    }
}