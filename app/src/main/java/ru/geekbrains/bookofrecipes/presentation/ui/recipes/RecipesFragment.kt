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
import kotlinx.android.synthetic.main.detal_fragment.*
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.presentation.ui.details.DetailsFragment
import ru.geekbrains.bookofrecipes.presentation.ui.recycler.RecipesAdapter

class RecipesFragment : Fragment() {

    private val recipesViewModel: RecipesViewModel by viewModel()
    private val recipesAdapter: RecipesAdapter = get()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showDetail()

        val detalFragment = DetailsFragment()
        detalFragment.sharedElementEnterTransition = MaterialContainerTransform()




    }
    private fun showDetail(){
        navigation_icon.setOnClickListener {
            val extras = FragmentNavigatorExtras((view to "shared_element_container") as Pair<View, String>)
            findNavController().navigate(R.id.action_navigation_recipes_to_navigation_detail
                ,null,null,extras)
        }
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

//
}