package ru.geekbrains.bookofrecipes.presentation.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRandomRecipes
import ru.geekbrains.bookofrecipes.presentation.models.RecipeView

class RecipesViewModel(private val getRandomRecipes: GetRandomRecipes) : ViewModel() {

    private val _recipes = MutableLiveData<List<RecipeView>>()
    val recipes: LiveData<List<RecipeView>> = _recipes

    fun loadRandomRecipes() = getRandomRecipes(10) {
        _recipes.value = it.recipes.map { recipeInfo ->
            RecipeView(
                recipeInfo.dishImageUrl,
                recipeInfo.dishName,
                recipeInfo.dishSummary
            )
        }
    }
}