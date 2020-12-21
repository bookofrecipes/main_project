package ru.geekbrains.bookofrecipes.service.di.module

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.domain.Repository
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRandomRecipes
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRecipeInformationBulk
import ru.geekbrains.bookofrecipes.domain.use_cases.GetRecipesByIngredients
import ru.geekbrains.bookofrecipes.presentation.ui.recipes.RecipesViewModel
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter
import ru.geekbrains.bookofrecipes.presentation.ui.searching.SearchDialogFragment
import ru.geekbrains.bookofrecipes.service.utils.NetworkAvailabilityHandler

val appModule = module {
    factory { (listener: RecipesAdapter.RecipesAdapterListener) -> RecipesAdapter(listener) }
    single { NetworkAvailabilityHandler(androidContext()) }
}

val repoModule = module {
    single { RecipesViewModel(get(), get(), get()) }

    single { GetRandomRecipes(get()) }
    single { GetRecipesByIngredients(get()) }
    single { GetRecipeInformationBulk(get()) }

    single<Repository> { RecipesRepository(get()) }

    single { SearchDialogFragment() }
}