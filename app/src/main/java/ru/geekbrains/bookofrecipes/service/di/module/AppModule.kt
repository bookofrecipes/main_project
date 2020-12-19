package ru.geekbrains.bookofrecipes.service.di.module

import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.data.local.RecipeDatabase
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

val databaseModule = module {
    single { provideDB(androidContext()) }
    single { get<RecipeDatabase>().recipeDao() }
}

internal fun provideDB(_context: Context) =
    Room.databaseBuilder(
    _context,
    RecipeDatabase::class.java,
    "recipe_db"
)
    .fallbackToDestructiveMigration()
.build()
