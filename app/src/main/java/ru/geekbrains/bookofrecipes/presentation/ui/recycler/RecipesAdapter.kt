package ru.geekbrains.bookofrecipes.presentation.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recyclerview_item.view.*
import ru.geekbrains.bookofrecipes.presentation.models.RecipeView
import kotlin.properties.Delegates
import ru.geekbrains.bookofrecipes.R

class RecipesAdapter() : RecyclerView.Adapter<RecipesAdapter.RecipesHolder>() {

    internal var collection: List<RecipeView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesHolder {
        return RecipesHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipesHolder, position: Int) {
        holder.bind(collection[position])
    }

    override fun getItemCount(): Int = collection.size

    class RecipesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recipeView: RecipeView) {
            itemView.title_dish.text = recipeView.title
//            itemView.recipes_text.text = recipeView.summary
        }
    }
}