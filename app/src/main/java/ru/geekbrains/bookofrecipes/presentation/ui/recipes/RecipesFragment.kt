package ru.geekbrains.bookofrecipes.presentation.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.geekbrains.bookofrecipes.R

class RecipesFragment : Fragment() {

    private val recipesViewModel: RecipesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_recipes, container, false)

        recipesViewModel.text.observe(viewLifecycleOwner, {
            root.text_recipes.text = it
        })

        root.button.setOnClickListener {
            recipesViewModel.loadRandomRecipes()
        }

        return root
    }
}