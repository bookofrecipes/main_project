package ru.geekbrains.bookofrecipes.service.di.module

import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.geekbrains.bookofrecipes.data.RecipesRepository
import ru.geekbrains.bookofrecipes.data.local.DbHelper
import ru.geekbrains.bookofrecipes.data.local.LocalDataSource
import ru.geekbrains.bookofrecipes.data.local.db.RecipeDatabase
import ru.geekbrains.bookofrecipes.domain.Repository
import ru.geekbrains.bookofrecipes.domain.use_cases.*
import ru.geekbrains.bookofrecipes.presentation.ui.favorites.FavoritesViewModel
import ru.geekbrains.bookofrecipes.presentation.ui.recipes.RecipesViewModel
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter
import ru.geekbrains.bookofrecipes.presentation.ui.searching.SearchDialogFragment
import ru.geekbrains.bookofrecipes.service.utils.NetworkAvailabilityHandler

val appModule = module {
    factory { (listener: RecipesAdapter.RecipesAdapterListener) -> RecipesAdapter(listener) }
    single { NetworkAvailabilityHandler(androidContext()) }
}

val repoModule = module {
    single { RecipesViewModel(get(), get(), get(), get()) }
    single { FavoritesViewModel(get()) }

    single { GetRandomRecipes(get()) }
    single { GetRecipesByIngredients(get()) }
    single { GetRecipeInformationBulk(get()) }
    single { SaveRecipeToFavorites(get()) }

    single { GetFavoritesRecipes(get()) }

    single<Repository> { RecipesRepository(get(), get()) }

    single { SearchDialogFragment() }

}

val databaseModule = module {
    single { provideDB(androidContext()) }
    single { get<RecipeDatabase>().recipeDao() }
    single<LocalDataSource> { DbHelper(get()) }
}

internal fun provideDB(_context: Context) =
    Room.databaseBuilder(
        _context,
        RecipeDatabase::class.java,
        "recipe_db"
    )
        .fallbackToDestructiveMigration()
        .build()
