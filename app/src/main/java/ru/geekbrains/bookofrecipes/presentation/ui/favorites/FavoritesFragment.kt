package ru.geekbrains.bookofrecipes.presentation.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorites.view.*
import org.koin.android.ext.android.inject
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.presentation.ui.BaseListFragment
import ru.geekbrains.bookofrecipes.service.extensions.observeData
import ru.geekbrains.bookofrecipes.service.extensions.observeFailure

class FavoritesFragment : BaseListFragment() {

    private val favoritesViewModel: FavoritesViewModel by inject()

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
        root.favorites_recyclerview.layoutManager = LinearLayoutManager(root.context)
        root.favorites_recyclerview.adapter = listAdapter
    }

    override fun onRecipeClick(recipeView: View, recipeData: RecipeInformation) {
        val recipeCardDetailTransitionName = getString(R.string.recipe_card_detail_transition_name)
        val extras = FragmentNavigatorExtras((recipeView to recipeCardDetailTransitionName))
        val d = FavoritesFragmentDirections.actionNavigationFavoritesToNavigationDetail(recipeData)

        findNavController().navigate(d, extras)
    }
}