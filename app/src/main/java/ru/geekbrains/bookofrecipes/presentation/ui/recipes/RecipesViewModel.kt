package ru.geekbrains.bookofrecipes.presentation.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRandomRecipes

class RecipesViewModel(private val getRandomRecipes: GetRandomRecipes) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is recipes Fragment"
    }
    val text: LiveData<String> = _text

    fun loadRandomRecipes() = getRandomRecipes(10) {
        _text.value = it.recipes[0].dishName
    }
}