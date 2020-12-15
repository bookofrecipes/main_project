package ru.geekbrains.bookofrecipes.service.di.module

import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.domain.Repository
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRandomRecipes
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRecipesByIngredients
import ru.geekbrains.bookofrecipes.presentation.ui.recipes.RecipesViewModel
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter
import ru.geekbrains.bookofrecipes.presentation.ui.searching.SearchDialogFragment
import ru.geekbrains.bookofrecipes.service.utils.NetworkAvailabilityHandler

val appModule = module {
//    single { RecipesAdapter() }
    single { NetworkAvailabilityHandler(androidContext()) }
}

val repoModule = module {
    viewModel { RecipesViewModel(get(), get()) }
    single { GetRandomRecipes(get()) }
    single { GetRecipesByIngredients(get()) }
    single { RecipesRepository(get()) as Repository }
    single { SearchDialogFragment() }

}