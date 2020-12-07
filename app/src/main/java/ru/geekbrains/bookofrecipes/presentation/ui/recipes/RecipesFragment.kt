package ru.geekbrains.bookofrecipes.presentation.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.presentation.ui.DetalFragment
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter

class RecipesFragment : Fragment() {

    private val recipesViewModel: RecipesViewModel by viewModel()
    private val recipesAdapter: RecipesAdapter = get()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detalFragment = DetalFragment()
        detalFragment.sharedElementEnterTransition = MaterialContainerTransform()
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
    }

    private fun initializeView(root: View) {
        root.activity_recyclerview.layoutManager = LinearLayoutManager(root.context)
        root.activity_recyclerview.adapter = recipesAdapter
    }

    override fun onImageClicked(cardView: View,) {
        val emailCardDetailTransitionName = getString(R.s)
        val extras = FragmentNavigatorExtras(cardView to emailCardDetailTransitionName)
        val directions = HomeFragmentDirections.actionHomeFragmentToEmailFragment(email.id)
        findNavController().navigate(directions, extras)
        // TODO: Set up MaterialElevationScale transition as exit and reenter transitions.
    }

}