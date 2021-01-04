package ru.geekbrains.bookofrecipes.presentation.ui.recycler

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recyclerview_item.view.*
import kotlin.properties.Delegates
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.service.extensions.inflate
import ru.geekbrains.bookofrecipes.service.extensions.loadFromUrl
import java.util.*

class RecipesAdapter(private val listener: RecipesAdapterListener) :
    RecyclerView.Adapter<RecipesAdapter.RecipesHolder>(), ItemTouchHelperAdapter {

    internal var collection: MutableList<RecipeInformation> by
    Delegates.observable(mutableListOf()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecipesHolder(listener, parent.inflate(R.layout.activity_recyclerview_item))

    override fun onBindViewHolder(holder: RecipesHolder, position: Int) =
        holder.bind(collection[position])

    override fun getItemCount() = collection.size

    //used to move up/down
    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
//        Log.e("onItemMove", "run ")
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(collection, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(collection, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
//        println("${collection[position].name}    swiped")
        listener.onItemSwiped(collection[position])
        collection.removeAt(position)
        notifyItemRemoved(position)
    }

    class RecipesHolder(private val listener: RecipesAdapterListener, itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(recipeView: RecipeInformation) {
            itemView.image_dish.loadFromUrl(recipeView.imageUrl)
            itemView.title_dish.text = recipeView.name
            itemView.setOnClickListener {
                listener.onRecipeClick(itemView, recipeView)
            }
            itemView.favorites_button.setOnClickListener {
                listener.onFavouriteIconClick(recipeView)
            }

            val recipeCardItemTransitionName =
                itemView.resources.getString(R.string.recipe_card_transition_name, recipeView.id)
            itemView.transitionName = recipeCardItemTransitionName
        }
    }

    interface RecipesAdapterListener {
        fun onRecipeClick(recipeView: View, recipeData: RecipeInformation)
        fun onItemSwiped(recipe : RecipeInformation)
        fun onFavouriteIconClick(recipeData: RecipeInformation)
    }
}
