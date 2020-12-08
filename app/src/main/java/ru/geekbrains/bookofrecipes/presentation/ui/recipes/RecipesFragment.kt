package ru.geekbrains.bookofrecipes.presentation.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.presentation.models.RecipeModelForRecycler
import ru.geekbrains.bookofrecipes.presentation.ui.details.DetailsFragment
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter

class RecipesFragment : Fragment(), RecipesAdapter.RecipesAdapterListener {

    private val recipesViewModel: RecipesViewModel by viewModel()
    private val recipesAdapter: RecipesAdapter = RecipesAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detailFragment = DetailsFragment()
        detailFragment.sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun onRecipeClick(recipeView: View, recipeData: RecipeModelForRecycler) {
        val recipeCardDetailTransitionName = getString(R.string.recipe_card_detail_transition_name)
        val extras = FragmentNavigatorExtras((recipeView to recipeCardDetailTransitionName))
        val d = RecipesFragmentDirections.actionNavigationRecipesToNavigationDetail(recipeData)

        findNavController().navigate(d, extras)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_recipes, container, false)

        recipesViewModel.recipes.observe(viewLifecycleOwner, {
            recipesAdapter.collection = it
        })

        root.button.setOnClickListener {
            recipesViewModel.loadRandomRecipes()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView(view)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun initializeView(root: View) {
        root.activity_recyclerview.layoutManager = LinearLayoutManager(root.context)
        root.activity_recyclerview.adapter = recipesAdapter
    }

//
}