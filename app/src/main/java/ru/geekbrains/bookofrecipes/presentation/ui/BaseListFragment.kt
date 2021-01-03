package ru.geekbrains.bookofrecipes.presentation.ui


import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.geekbrains.bookofrecipes.presentation.MainActivity
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter
import ru.geekbrains.bookofrecipes.service.Failure

abstract class BaseListFragment : Fragment(), RecipesAdapter.RecipesAdapterListener {
    protected val listAdapter: RecipesAdapter by inject { parametersOf(this) }

    protected open fun handleFailure(failure: Failure?) {}

    protected fun handleRecipeList(recipes: List<RecipeInformation>) {
        listAdapter.collection = recipes.toMutableList()
    }

    override fun onFavouriteIconClick(recipeData: RecipeInformation) {}

    protected fun showFailure(message: String, action: () -> Unit) {
        Snackbar.make(
            (activity as MainActivity).place_for_snack,
            message,
            Snackbar.LENGTH_LONG
        ).setAction("Reload") { action() }.show()
    }

   override fun onItemSwiped(recipe: RecipeInformation){}
}