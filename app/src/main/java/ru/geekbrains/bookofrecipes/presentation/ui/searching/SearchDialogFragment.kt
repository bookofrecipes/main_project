package ru.geekbrains.bookofrecipes.presentation.ui.searching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.fragment_search_dialog.*
import kotlinx.android.synthetic.main.fragment_search_dialog.view.*
import ru.geekbrains.bookofrecipes.R

class SearchDialogFragment : DialogFragment() {
    val liveIngredientsResponse: MutableLiveData<String> = MutableLiveData()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()
        val root = inflater.inflate(R.layout.fragment_search_dialog, container, true)

        root.btn_start_searching.setOnClickListener {
            liveIngredientsResponse.value = searchingByIngredients_EditText.text.toString()
            searchingByIngredients_EditText.text.clear()
            this.dismiss()
        }
        return root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 1)
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}