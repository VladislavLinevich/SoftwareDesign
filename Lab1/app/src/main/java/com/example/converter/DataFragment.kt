package com.example.converter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
    private lateinit var spinner: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner

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
            11 -> {
                if (edit.text.toString() == ""){
                    edit.text.replace(
                        start.coerceAtMost(end), start.coerceAtLeast(end),
                        "0.", 0, 2
                    )
                }
                else{
                    edit.text.replace(
                        start.coerceAtMost(end), start.coerceAtLeast(end),
                        ".", 0, 1
                    )
                    if (edit.text.toString().count { it == '.' } == 2){
                        edit.text.delete(edit.selectionStart - 1, edit.selectionStart)
                    }
                    if (edit.text.isNotEmpty() && edit.text.toString()[0] == '.'){
                        edit.text.insert(0, "0")
                    }
                }
            }
            12 -> {
                if (edit.selectionEnd - edit.selectionStart > 0) {
                    edit.text.delete(edit.selectionStart, edit.selectionEnd)
                    if (edit.text.isNotEmpty() && edit.text.toString()[0] == '.'){
                        edit.text.delete(0, 1)
                    }
                }
                else {
                    if (edit.selectionStart > 0)
                    {
                        edit.text.delete(edit.selectionStart - 1, edit.selectionStart)
                        if (edit.text.isNotEmpty() && edit.text.toString()[0] == '.'){
                            edit.text.delete(0, 1)
                        }
                    }
                }

            }
            13 -> {
                val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("label",edittext.text)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(activity?.applicationContext, "First editbox copied successfully", Toast.LENGTH_SHORT).show()
            }
            14 -> {
                val pos: Int = spinner2.selectedItemPosition

                spinner2.setSelection(spinner3.selectedItemPosition)
                spinner3.setSelection(pos)
                if(edittext.isFocused) {
                    edittext.text = edittext2.text
                }
                else {
                    edittext2.text = edittext.text
                }

            }
            15 -> {
                val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("label",edittext2.text)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(activity?.applicationContext, "Second editbox copied successfully", Toast.LENGTH_SHORT).show()
            }
            16 -> edit.text.clear()
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

    private fun Convert(InputNumber: Editable, ConvertNumber: Editable, CategorySpinner: Spinner,  InputSpinner: Spinner, ConvertSpinner: Spinner){
        val categories = resources.getStringArray(R.array.Categories)
        var names: Array<String>
        var num = InputNumber.toString().toDoubleOrNull()

        if (CategorySpinner.selectedItem == null || InputSpinner.selectedItem == null){
            return
        }
        if (num == null){
            ConvertNumber.clear()
        }
        else {
            if (CategorySpinner.selectedItem.toString() == categories[0]) {
                names = resources.getStringArray(R.array.Length)

                // From cm to others
                if (InputSpinner.selectedItem.toString() == names[0] && ConvertSpinner.selectedItem.toString() == names[0]) {
                    ConvertNumber.clear()
                    ConvertNumber.append(InputNumber.toString())
                }
                if (InputSpinner.selectedItem.toString() == names[0] && ConvertSpinner.selectedItem.toString() == names[1]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num / 100).toString())
                }
                if (InputSpinner.selectedItem.toString() == names[0] && ConvertSpinner.selectedItem.toString() == names[2]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num / 100000).toString())
                }

                // From m to others
                if (InputSpinner.selectedItem.toString() == names[1] && ConvertSpinner.selectedItem.toString() == names[0]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num * 100).toString())
                }
                if (InputSpinner.selectedItem.toString() == names[1] && ConvertSpinner.selectedItem.toString() == names[1]) {
                    ConvertNumber.clear()
                    ConvertNumber.append(InputNumber.toString())
                }
                if (InputSpinner.selectedItem.toString() == names[1] && ConvertSpinner.selectedItem.toString() == names[2]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num / 1000).toString())
                }

                // From km to others
                if (InputSpinner.selectedItem.toString() == names[2] && ConvertSpinner.selectedItem.toString() == names[0]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num * 100000).toString())
                }
                if (InputSpinner.selectedItem.toString() == names[2] && ConvertSpinner.selectedItem.toString() == names[1]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num * 1000).toString())
                }
                if (InputSpinner.selectedItem.toString() == names[2] && ConvertSpinner.selectedItem.toString() == names[2]) {
                    ConvertNumber.clear()
                    ConvertNumber.append(InputNumber.toString())
                }
            }
            if (CategorySpinner.selectedItem.toString() == categories[1]) {
                names = resources.getStringArray(R.array.Weight)

                // From g to others
                if (InputSpinner.selectedItem.toString() == names[0] && ConvertSpinner.selectedItem.toString() == names[0]) {
                    ConvertNumber.clear()
                    ConvertNumber.append(InputNumber.toString())
                }
                if (InputSpinner.selectedItem.toString() == names[0] && ConvertSpinner.selectedItem.toString() == names[1]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num / 1000).toString())
                }
                if (InputSpinner.selectedItem.toString() == names[0] && ConvertSpinner.selectedItem.toString() == names[2]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num / 1000000).toString())
                }

                // From kg to others
                if (InputSpinner.selectedItem.toString() == names[1] && ConvertSpinner.selectedItem.toString() == names[0]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num * 1000).toString())
                }
                if (InputSpinner.selectedItem.toString() == names[1] && ConvertSpinner.selectedItem.toString() == names[1]) {
                    ConvertNumber.clear()
                    ConvertNumber.append(InputNumber.toString())
                }
                if (InputSpinner.selectedItem.toString() == names[1] && ConvertSpinner.selectedItem.toString() == names[2]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num / 1000).toString())
                }

                // From t to others
                if (InputSpinner.selectedItem.toString() == names[2] && ConvertSpinner.selectedItem.toString() == names[0]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num * 1000000).toString())
                }
                if (InputSpinner.selectedItem.toString() == names[2] && ConvertSpinner.selectedItem.toString() == names[1]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num * 1000).toString())
                }
                if (InputSpinner.selectedItem.toString() == names[2] && ConvertSpinner.selectedItem.toString() == names[2]) {
                    ConvertNumber.clear()
                    ConvertNumber.append(InputNumber.toString())
                }
            }
            if (CategorySpinner.selectedItem.toString() == categories[2]) {
                names = resources.getStringArray(R.array.Time)

                // From s to others
                if (InputSpinner.selectedItem.toString() == names[0] && ConvertSpinner.selectedItem.toString() == names[0]) {
                    ConvertNumber.clear()
                    ConvertNumber.append(InputNumber.toString())
                }
                if (InputSpinner.selectedItem.toString() == names[0] && ConvertSpinner.selectedItem.toString() == names[1]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num / 60).toString())
                }
                if (InputSpinner.selectedItem.toString() == names[0] && ConvertSpinner.selectedItem.toString() == names[2]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num / 3600).toString())
                }

                // From min to others
                if (InputSpinner.selectedItem.toString() == names[1] && ConvertSpinner.selectedItem.toString() == names[0]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num * 60).toString())
                }
                if (InputSpinner.selectedItem.toString() == names[1] && ConvertSpinner.selectedItem.toString() == names[1]) {
                    ConvertNumber.clear()
                    ConvertNumber.append(InputNumber.toString())
                }
                if (InputSpinner.selectedItem.toString() == names[1] && ConvertSpinner.selectedItem.toString() == names[2]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num / 60).toString())
                }

                // From h to others
                if (InputSpinner.selectedItem.toString() == names[2] && ConvertSpinner.selectedItem.toString() == names[0]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num * 3600).toString())
                }
                if (InputSpinner.selectedItem.toString() == names[2] && ConvertSpinner.selectedItem.toString() == names[1]) {
                    ConvertNumber.clear()
                    ConvertNumber.append((num * 60).toString())
                }
                if (InputSpinner.selectedItem.toString() == names[2] && ConvertSpinner.selectedItem.toString() == names[2]) {
                    ConvertNumber.clear()
                    ConvertNumber.append(InputNumber.toString())
                }
            }
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
        spinner = view.findViewById(R.id.spinner4)
        spinner2 = view.findViewById(R.id.spinner)
        spinner3 = view.findViewById(R.id.spinner3)
        spinner.adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, categories)
        edittext = view.findViewById(R.id.editTextNumberDecimal)
        edittext2 = view.findViewById(R.id.editTextNumberDecimal2)
        edittext.showSoftInputOnFocus = false
        edittext2.showSoftInputOnFocus = false

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                var names = resources.getStringArray(R.array.Length)
                if (spinner.selectedItem.toString() == categories[0]){
                    names = resources.getStringArray(R.array.Length)
                    if(edittext.text.toString() != "") {
                        Convert(edittext.text, edittext2.text, spinner, spinner2, spinner3)
                    }
                }
                if (spinner.selectedItem.toString() == categories[1]){
                    names = resources.getStringArray(R.array.Weight)
                    if(edittext.text.toString() != "") {
                        Convert(edittext.text, edittext2.text, spinner, spinner2, spinner3)
                    }
                }
                if (spinner.selectedItem.toString() == categories[2]){
                    names = resources.getStringArray(R.array.Time)
                    if(edittext.text.toString() != "") {
                        Convert(edittext.text, edittext2.text, spinner, spinner2, spinner3)
                    }
                }
                spinner2.adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, names)
                spinner3.adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, names)
                if (savedInstanceState != null) {
                    spinner2.setSelection(savedInstanceState.getInt("savedFirstSpinner"))
                    spinner3.setSelection(savedInstanceState.getInt("savedSecondSpinner"))
                }
            }
        }

        spinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                if(edittext.isFocused && edittext.text.toString() != "") {
                    Convert(edittext.text, edittext2.text, spinner, spinner2, spinner3)
                }
                if(edittext2.isFocused && edittext.text.toString() != ""){
                    Convert(edittext2.text, edittext.text, spinner, spinner3, spinner2)
                }
            }
        }

        spinner3?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                if(edittext.isFocused && edittext.text.toString() != "") {
                    Convert(edittext.text, edittext2.text, spinner, spinner2, spinner3)
                }
                if(edittext2.isFocused && edittext.text.toString() != ""){
                    Convert(edittext2.text, edittext.text, spinner, spinner3, spinner2)
                }
            }
        }

        edittext.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (s.toString() != "" && edittext.isFocused) {
                    Convert(edittext.text, edittext2.text, spinner, spinner2, spinner3)
                }
                if (s.toString() == "" && edittext.isFocused) {
                    edittext2.text.clear()
                }

            }
        })
        edittext2.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (s.toString() != "" && edittext2.isFocused) {
                    Convert(edittext2.text, edittext.text, spinner, spinner3, spinner2)
                }
                if(s.toString() == "" && edittext2.isFocused) {
                    edittext.text.clear()
                }

            }
        })

        if (savedInstanceState != null){
            edittext.text.append("1")
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)

        outState.putInt("savedFirstSpinner", spinner2.selectedItemPosition)
        outState.putInt("savedSecondSpinner", spinner3.selectedItemPosition)

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