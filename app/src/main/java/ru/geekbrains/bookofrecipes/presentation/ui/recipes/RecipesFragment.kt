package ru.geekbrains.bookofrecipes.presentation.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.presentation.MainActivity
import ru.geekbrains.bookofrecipes.presentation.models.RecipeModelForRecycler
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter
import ru.geekbrains.bookofrecipes.presentation.ui.searching.SearchDialogFragment
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.Failure.NetworkConnection
import ru.geekbrains.bookofrecipes.service.Failure.ServerError
import ru.geekbrains.bookofrecipes.service.extensions.observeData
import ru.geekbrains.bookofrecipes.service.extensions.observeFailure

class RecipesFragment : Fragment() {

    private val recipesViewModel: RecipesViewModel by viewModel()
    private val recipesAdapter: RecipesAdapter = get()
    private lateinit var floatingActionButton : FloatingActionButton

    private  var searchDialogFragment: SearchDialogFragment = SearchDialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_recipes, container, false)

        floatingActionButton  = root.findViewById(R.id.search_fab)

        floatingActionButton.setOnClickListener {
            activity?.supportFragmentManager?.let{ searchDialogFragment.show(it, "MyCustomFragment")}
        }

        observeData(recipesViewModel.recipes, ::handleRecipeList)
        observeFailure(recipesViewModel.failure, ::handleFailure)

        root.button.setOnClickListener {
            recipesViewModel.loadRandomRecipes()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView(view)
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
