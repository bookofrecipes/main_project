package ru.geekbrains.bookofrecipes.service.di.module

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRandomRecipes
import ru.geekbrains.bookofrecipes.presentation.ui.recipes.RecipesViewModel
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter

val appModule = module {
    single { RecipesAdapter() }
}

val repoModule = module {
    viewModel { RecipesViewModel(get()) }
    single { GetRandomRecipes(get()) }
    single { RecipesRepository(get()) }
}