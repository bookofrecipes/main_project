package ru.geekbrains.bookofrecipes.presentation.ui.favorites

import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorites.view.*
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.presentation.ui.BaseListFragment
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.SimpleItemTouchHelperCallback
import ru.geekbrains.bookofrecipes.service.androidtools.vibrate
import ru.geekbrains.bookofrecipes.service.extensions.observeData
import ru.geekbrains.bookofrecipes.service.extensions.observeFailure

class FavoritesFragment : BaseListFragment() {

    private val favoritesViewModel: FavoritesViewModel by inject()
    lateinit var callback : ItemTouchHelper.Callback
    private val vibrator: Vibrator by inject()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)

        observeData(favoritesViewModel.recipesInfo, ::handleRecipeList)
        observeFailure(favoritesViewModel.failure, ::handleFailure)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView(view)

        favoritesViewModel.loadFavoritesRecipes()
    }

    private fun initializeView(root: View) {
        root.favorites_recyclerview.apply {
            layoutManager = LinearLayoutManager(root.context)
            adapter = listAdapter
        }
        callback = SimpleItemTouchHelperCallback(root.favorites_recyclerview.adapter as RecipesAdapter)
        ItemTouchHelper(callback).attachToRecyclerView(root.favorites_recyclerview)
    }

    override fun onRecipeClick(recipeView: View, recipeData: RecipeInformation) {
        val recipeCardDetailTransitionName = getString(R.string.recipe_card_detail_transition_name)
        val extras = FragmentNavigatorExtras((recipeView to recipeCardDetailTransitionName))
        val d = FavoritesFragmentDirections.actionNavigationFavoritesToNavigationDetail(recipeData)

        findNavController().navigate(d, extras)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemSwiped(recipe: RecipeInformation) {
        favoritesViewModel.deleteRecipeFromFavorites(recipe)
        vibrate(vibrator)
    }
}