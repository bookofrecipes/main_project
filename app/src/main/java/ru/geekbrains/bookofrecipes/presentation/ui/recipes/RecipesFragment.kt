package ru.geekbrains.bookofrecipes.presentation.ui.recipes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.presentation.ui.details.DetailsFragment
import ru.geekbrains.bookofrecipes.presentation.MainActivity
import ru.geekbrains.bookofrecipes.presentation.models.RecipeModelForRecycler
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter
import ru.geekbrains.bookofrecipes.presentation.ui.searching.SearchDialogFragment
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.Failure.NetworkConnection
import ru.geekbrains.bookofrecipes.service.Failure.ServerError
import ru.geekbrains.bookofrecipes.service.extensions.observeData
import ru.geekbrains.bookofrecipes.service.extensions.observeFailure

private const val TARGET_FRAGMENT_REQUEST_CODE = 1
private const val EXTRA_GREETING_MESSAGE = "message"

class RecipesFragment : Fragment(), RecipesAdapter.RecipesAdapterListener {

    private val recipesViewModel: RecipesViewModel by viewModel()
    private val recipesAdapter: RecipesAdapter by inject { parametersOf(this) }
    private val searchDialogFragment: SearchDialogFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchDialogFragment.setTargetFragment(this, TARGET_FRAGMENT_REQUEST_CODE)
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

        root.search_fab.setOnClickListener {
            parentFragmentManager.let {
                searchDialogFragment.show(it, "SearchingDialogFragment")
            }
        }

        root.button.setOnClickListener {
            recipesViewModel.loadRandomRecipes()
        }

        observeData(recipesViewModel.recipes, ::handleRecipeList)
        observeFailure(recipesViewModel.failure, ::handleFailure)
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            Log.e(
                "RecipesFragment",
                "resultCode = $requestCode doesn't equal to Activity.Result_OK"
            )
            return
        }
        if (requestCode == TARGET_FRAGMENT_REQUEST_CODE) {
            data?.getStringExtra(EXTRA_GREETING_MESSAGE)?.let {
                recipesViewModel.loadRecipesByIngredients(it)
            }
        }
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

    private fun handleRecipeList(list: List<RecipeModelForRecycler>) {
        recipesAdapter.collection = list
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            NetworkConnection -> showFailure("No network connection on your device")
            ServerError -> showFailure("Server error. Please, try again later.")
            else -> return
        }
    }

    private fun showFailure(message: String) {
        val snackbar = Snackbar.make(
            (activity as MainActivity).nav_host_fragment,
            message,
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction("Reload") { recipesViewModel.loadRandomRecipes() }
        snackbar.show()
    }
}
