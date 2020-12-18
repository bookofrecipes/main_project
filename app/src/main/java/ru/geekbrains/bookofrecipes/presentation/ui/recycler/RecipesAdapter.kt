package ru.geekbrains.bookofrecipes.presentation.ui.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recyclerview_item.view.*
import ru.geekbrains.bookofrecipes.presentation.models.RecipeModelForRecycler
import kotlin.properties.Delegates
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.service.extensions.inflate
import ru.geekbrains.bookofrecipes.service.extensions.loadFromUrl

class RecipesAdapter(private val listener: RecipesAdapterListener) :
    RecyclerView.Adapter<RecipesAdapter.RecipesHolder>() {

    internal var collection: List<RecipeModelForRecycler> by
    Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecipesHolder(listener, parent.inflate(R.layout.activity_recyclerview_item))


    override fun onBindViewHolder(holder: RecipesHolder, position: Int) =
        holder.bind(collection[position])

    override fun getItemCount() = collection.size

    class RecipesHolder(private val listener: RecipesAdapterListener, itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(recipeView: RecipeModelForRecycler) {
            itemView.image_dish.loadFromUrl(recipeView.imageUrl)
            itemView.title_dish.text = recipeView.title
            itemView.setOnClickListener {
                listener.onRecipeClick(itemView, recipeView)
            }

            val recipeCardItemTransitionName =
                itemView.resources.getString(R.string.recipe_card_transition_name, recipeView.id)
            itemView.transitionName = recipeCardItemTransitionName

//            itemView.recipes_text.text = recipeView.summary
        }
    }


    interface RecipesAdapterListener {
        fun onRecipeClick(recipeView: View, recipeData: RecipeModelForRecycler)
    }
}
