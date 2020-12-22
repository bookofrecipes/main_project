package ru.geekbrains.bookofrecipes.presentation.ui.recycler

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recyclerview_item.view.*
import kotlin.properties.Delegates
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.service.extensions.inflate
import ru.geekbrains.bookofrecipes.service.extensions.loadFromUrl

class RecipesAdapter(private val listener: RecipesAdapterListener) :
    RecyclerView.Adapter<RecipesAdapter.RecipesHolder>() {

    internal var collection: List<RecipeInformation> by
    Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecipesHolder(listener, parent.inflate(R.layout.activity_recyclerview_item))

    override fun onBindViewHolder(holder: RecipesHolder, position: Int) =
        holder.bind(collection[position])

    override fun getItemCount() = collection.size

    class RecipesHolder(private val listener: RecipesAdapterListener, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
        fun bind(recipeView: RecipeInformation) {
            itemView.image_dish.loadFromUrl(recipeView.imageUrl)
            itemView.title_dish.text = recipeView.name
            itemView.setOnClickListener {
                listener.onRecipeClick(itemView, recipeView)
            }
            itemView.btn_favorites.setOnClickListener {
                listener.onFavouriteIconClick(recipeView)
                itemView.btn_favorites.icon =
                    itemView.resources.getDrawable(R.drawable.ic_baseline_favorite_24, null)
            }

            itemView.btn_favorites.icon = itemView.resources.getDrawable(
                if (recipeView.isFavorite)
                    R.drawable.ic_baseline_favorite_24
                else
                    R.drawable.ic_baseline_favorite_border_24,
                null
            )

            itemView.likes_text.text = " ${recipeView.likes}"

            itemView.time_text.text = " ${recipeView.cookTimeInMinutes} min"

            val recipeCardItemTransitionName =
                itemView.resources.getString(R.string.recipe_card_transition_name, recipeView.id)
            itemView.transitionName = recipeCardItemTransitionName
        }
    }

    interface RecipesAdapterListener {
        fun onRecipeClick(recipeView: View, recipeData: RecipeInformation)

        fun onFavouriteIconClick(recipeData: RecipeInformation)
    }
}
