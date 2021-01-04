package ru.geekbrains.bookofrecipes.presentation.ui.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.bookofrecipes.domain.use_cases.DeleteRecipeFromFavorites
import ru.geekbrains.bookofrecipes.domain.use_cases.GetFavoritesRecipes
import ru.geekbrains.bookofrecipes.domain.use_cases.UseCase
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.presentation.ui.BaseViewModel

class FavoritesViewModel(
        private val getFavoritesRecipes: GetFavoritesRecipes,
        private val deleteRecipe: DeleteRecipeFromFavorites
) : BaseViewModel() {

    private val _recipesInfo = MutableLiveData<List<RecipeInformation>>()
    val recipesInfo: LiveData<List<RecipeInformation>> = _recipesInfo

    fun loadFavoritesRecipes() {
        getFavoritesRecipes(UseCase.None()) { it.fold(::handleFailure, ::handleFavoritesRecipes) }
    }

    private fun handleFavoritesRecipes(recipes: List<RecipeInformation>) {
        _recipesInfo.value = recipes
    }

    fun deleteRecipeFromFavorites(recipeInformation: RecipeInformation){
        deleteRecipe(recipeInformation){it.fold(::handleFailure, ::handleFavoritesDeleting)
        }
    }
    private fun handleFavoritesDeleting(id: Long) {
        Log.d("Room id deleted: ", id.toString())
    }
}