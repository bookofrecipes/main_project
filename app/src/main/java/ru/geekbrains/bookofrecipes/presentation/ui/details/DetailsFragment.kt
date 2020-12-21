package ru.geekbrains.bookofrecipes.presentation.ui.details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.detail_fragment.view.*
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.presentation.models.RecipeInformation
import ru.geekbrains.bookofrecipes.service.extensions.loadFromUrl
import ru.geekbrains.bookofrecipes.service.extensions.themeColor

class DetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.reply_motion_duration_small).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.navigation_icon.setOnClickListener {
            findNavController().navigateUp()
        }

        val recipeData = arguments?.getSerializable("recipe") as RecipeInformation

        view.title_detail_dish.text = recipeData.name
        view.full_detail_dish.text = recipeData.summary
        view.detail_image_dish.loadFromUrl(recipeData.imageUrl)
    }
}