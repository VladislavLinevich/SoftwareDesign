package com.example.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var edittext: EditText
    private lateinit var edittext2: EditText

    private fun setEditText(buttonIndex: Int, edit: EditText){
        val start = edit.selectionStart.coerceAtLeast(0)
        val end = edit.selectionEnd.coerceAtLeast(0)
        when (buttonIndex) {
            1 -> edit.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "1", 0, 1)
            2 -> edit.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "2", 0, 1)
            3 -> edit.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "3", 0, 1)
            4 -> edit.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "4", 0, 1)
            5 -> edit.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "5", 0, 1)
            6 -> edit.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "6", 0, 1)
            7 -> edit.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "7", 0, 1)
            8 -> edit.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "8", 0, 1)
            9 -> edit.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "9", 0, 1)
            10 -> edit.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "0", 0, 1)
            11 -> edit.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                ".", 0, 1)
            12 -> {
                if (edit.selectionEnd - edit.selectionStart > 0) {
                    edit.text.delete(edit.selectionStart, edit.selectionEnd)
                }
                else {
                    if (edit.selectionStart > 0)
                    {
                        edit.text.delete(edit.selectionStart - 1, edit.selectionStart)
                    }
                }

            }
            else -> {
            }
        }
    }

    fun setDescription(buttonIndex: Int) {
        if(edittext.isFocused)
        {
            setEditText(buttonIndex, edittext)
        }

        if(edittext2.isFocused)
        {
            setEditText(buttonIndex, edittext2)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val categories = resources.getStringArray(R.array.Categories)
        val spinner: Spinner = view.findViewById(R.id.spinner4)
        val spinner2: Spinner = view.findViewById(R.id.spinner)
        val spinner3: Spinner = view.findViewById(R.id.spinner3)
        spinner.adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, categories)

        edittext = view.findViewById(R.id.editTextNumberDecimal)
        edittext2 = view.findViewById(R.id.editTextNumberDecimal2)
        edittext.showSoftInputOnFocus = false
        edittext2.showSoftInputOnFocus = false

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                val choose = resources.getStringArray(R.array.Categories)
                var names = resources.getStringArray(R.array.Length)
                if (choose[spinner.selectedItemPosition] == choose[0]){
                    names = resources.getStringArray(R.array.Length)
                }
                if (choose[spinner.selectedItemPosition] == choose[1]){
                    names = resources.getStringArray(R.array.Weight)
                }
                if (choose[spinner.selectedItemPosition] == choose[2]){
                    names = resources.getStringArray(R.array.Time)
                }
                spinner2.adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, names)
                spinner3.adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, names)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DataFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DataFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}