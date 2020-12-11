package ru.geekbrains.bookofrecipes.presentation.ui.searching

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_search_dialog.*
import kotlinx.android.synthetic.main.fragment_search_dialog.view.*
import ru.geekbrains.bookofrecipes.R


class SearchDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search_dialog, container, true)

        root.btn_start_searching.setOnClickListener {
            sendSearchString()
        }

        root.searchingByIngredients_EditText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    sendSearchString()
                    true
                }
                else -> false
            }
        }

        return root
    }

    private fun sendSearchString() {
        val result = searchingByIngredients_EditText.text.toString()
        if (result.isNotEmpty()) {
            sendResult(result)
        }
    }

    private fun sendResult(message: String) {
        targetFragment ?: return
        val intent = Intent().putExtra("message", message)
        targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        searchingByIngredients_EditText.text?.clear()
        dismiss()
    }
}