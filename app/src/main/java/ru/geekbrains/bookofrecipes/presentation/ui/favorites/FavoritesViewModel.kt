package ru.geekbrains.bookofrecipes.presentation.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.bookofrecipes.domain.use_cases.GetFavoritesRecipes
import ru.geekbrains.bookofrecipes.domain.use_cases.UseCase
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.presentation.ui.BaseViewModel

class FavoritesViewModel(private val getFavoritesRecipes: GetFavoritesRecipes) : BaseViewModel() {

    private val _recipesInfo = MutableLiveData<List<RecipeInformation>>()
    val recipesInfo: LiveData<List<RecipeInformation>> = _recipesInfo

    fun loadFavoritesRecipes() {
        getFavoritesRecipes(UseCase.None()) { it.fold(::handleFailure, ::handleFavoritesRecipes) }
    }

    private fun handleFavoritesRecipes(recipes: List<RecipeInformation>) {
        _recipesInfo.value = recipes
    }
}