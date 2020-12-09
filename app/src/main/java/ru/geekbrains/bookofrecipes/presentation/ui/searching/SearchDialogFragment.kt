package ru.geekbrains.bookofrecipes.presentation.ui.searching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import kotlinx.android.synthetic.main.fragment_search_dialog.*
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.presentation.MainActivity
import ru.geekbrains.bookofrecipes.presentation.models.RecipeModelForRecycler
import ru.geekbrains.bookofrecipes.presentation.ui.recipes.RecipesViewModel
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.extensions.observeData
import ru.geekbrains.bookofrecipes.service.extensions.observeFailure

class SearchDialogFragment : DialogFragment() {
    private val searchingRecipesViewModel: SearchingViewModel by viewModel()
    private val recipesAdapter: RecipesAdapter = get()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()
        val root = inflater.inflate(R.layout.fragment_search_dialog, container, false)

        observeData(searchingRecipesViewModel.recipes, ::handleRecipeList)
        observeFailure(searchingRecipesViewModel.failure, ::handleFailure)

        return root
    }

    private fun handleRecipeList(list: List<RecipeModelForRecycler>) {
        recipesAdapter.collection = list
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun getTextFromEditText():String = searchingByIngredients_EditText.text.toString()

    fun getRecipesByIngredients(){
        searchingRecipesViewModel.loadRecipes(getTextFromEditText(),10)
    }
    private fun handleFailure(failure: Failure?) {
        when (failure) {
            Failure.NetworkConnection -> showFailure("No network connection on your device")
            Failure.ServerError -> showFailure("Server error. Please, try again later.")
            else -> return
        }
    }

    private fun showFailure(message: String) {
        val snackbar = Snackbar.make(
                (activity as MainActivity).nav_host_fragment,
                message,
                Snackbar.LENGTH_LONG
        )
        snackbar.setAction("Reload") { getRecipesByIngredients() }
        snackbar.show()
    }



}