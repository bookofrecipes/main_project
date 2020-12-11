package ru.geekbrains.bookofrecipes.presentation.ui.searching

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_search_dialog.*
import kotlinx.android.synthetic.main.fragment_search_dialog.view.*
import ru.geekbrains.bookofrecipes.R


class SearchDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()
        val root = inflater.inflate(R.layout.fragment_search_dialog, container, true)

        root.btn_start_searching.setOnClickListener {
            val request = searchingByIngredients_EditText.text.toString()
            sendResult(request)
        }
        return root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 1)
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun sendResult(message: String) {
        targetFragment?: return
        val intent = Intent().putExtra("message",message)
        targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        searchingByIngredients_EditText.text.clear()
        dismiss()
    }
}