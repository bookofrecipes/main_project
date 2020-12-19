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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.data.local.entity.Ingredient
import ru.geekbrains.bookofrecipes.data.local.entity.Recipe
import ru.geekbrains.bookofrecipes.data.local.db.RecipeDao
import ru.geekbrains.bookofrecipes.data.local.entity.cross_ref.RecipeIngredientCrossRef
import ru.geekbrains.bookofrecipes.presentation.ui.details.DetailsFragment
import ru.geekbrains.bookofrecipes.presentation.MainActivity
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
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

    val dao : RecipeDao = get()
    private val recipesViewModel: RecipesViewModel by inject()
    private val recipesAdapter: RecipesAdapter by inject { parametersOf(this) }
    private val searchDialogFragment: SearchDialogFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchDialogFragment.setTargetFragment(this, TARGET_FRAGMENT_REQUEST_CODE)
        val detailFragment = DetailsFragment()
        detailFragment.sharedElementEnterTransition = MaterialContainerTransform()

///////////////////////////////////////////
//        val recipes = listOf(
//            Recipe(
//                1,
//                "www.pic1.com",
//                13,
//                "cool hot spicy",
//                "pasta"
//            ),
//            Recipe(
//                2,
//                "www.pic2.com",
//                12,
//                "cold",
//                "ice cream"
//            ),
//            Recipe(
//                3,
//                "www.pic3.com",
//                4,
//                "warm",
//                "soup"
//            ),
//            Recipe(
//                4,
//                "www.pic4.com",
//                46,
//                "spicy",
//                "roll"
//            ),
//            Recipe(
//                5,
//                "www.pic5.com",
//                8,
//                "black",
//                "pizza"
//            ),
//            Recipe(
//                6,
//                "www.pic6.com",
//                43,
//                "white",
//                "barbecue"
//            ),
//            Recipe(
//                7,
//                "www.pic7.com",
//                77,
//                "liquid",
//                "brad"
//            )
//        )
//        val ingredients = listOf(
//            Ingredient(
//                1,
//                "photo1",
//                "tomato",
//                "red"
//            ),
//            Ingredient(
//                2,
//                "photo2",
//                "mushroom",
//                "white"
//            ),
//            Ingredient(
//                3,
//                "photo3",
//                "rice",
//                "japanese"
//            ),
//            Ingredient(
//                4,
//                "photo4",
//                "water",
//                "clean"
//            ),
//            Ingredient(
//                5,
//                "photo5",
//                "juice",
//                "orange juice"
//            ),
//            Ingredient(
//                6,
//                "photo6",
//                "apple",
//                "winter"
//            )
//        )
//
//        val recipeIngredientsRelations = listOf(
//            RecipeIngredientCrossRef(
//                1,
//                3
//            ),
//            RecipeIngredientCrossRef(
//                1,
//                4
//            ),
//            RecipeIngredientCrossRef(
//                1,
//                6
//            ),
//            RecipeIngredientCrossRef(
//                2,
//                1
//            ),
//            RecipeIngredientCrossRef(
//                3,
//                2
//            ),
//            RecipeIngredientCrossRef(
//                3,
//                4
//            ),
//            RecipeIngredientCrossRef(
//                4,
//                2
//            ),
//            RecipeIngredientCrossRef(
//                5,
//                7
//            ),
//            RecipeIngredientCrossRef(
//                6,
//                7
//            ),
//            RecipeIngredientCrossRef(
//                7,
//                2
//            ),
//            RecipeIngredientCrossRef(
//                7,
//                3
//            )
//        )
//        lifecycleScope.launch {
//            recipes.forEach { dao.insertRecipe(it) }
//            ingredients.forEach { dao.insertIngredient(it) }
//            recipeIngredientsRelations.forEach { dao.insertRecipeIngredientCrossRef(it) }
//
//            Log.e("DataBase TESTING", "${dao.getIngredientsOfRecipe(7)}")
//        }
//////////////////////////////////////////////////////




    }

    override fun onRecipeClick(recipeView: View, recipeData: RecipeInformation) {
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

        observeData(recipesViewModel.recipesInfo, ::handleRecipeList)
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

        recipesViewModel.recipesInfo.value?.let { handleRecipeList(it) }
    }

    private fun initializeView(root: View) {
        root.activity_recyclerview.layoutManager = LinearLayoutManager(root.context)
        root.activity_recyclerview.adapter = recipesAdapter
    }

    private fun handleRecipeList(list: List<RecipeInformation>) {
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
        Snackbar.make(
            (activity as MainActivity).place_for_snack,
            message,
            Snackbar.LENGTH_LONG
        ).setAction("Reload") { recipesViewModel.lastRequestMade() }.show()
    }
}
