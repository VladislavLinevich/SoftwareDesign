package com.example.calculator

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.mariuszgromada.math.mxparser.Expression

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
    private lateinit var textView: TextView
    private var openBrackets: Int = 0

    private fun constInput(start: Int, end: Int, number: Char){
        if (start != 0 )
        {
            when (edittext.text.toString()[start - 1]){
                '-', '+', '×', '÷', '(', '√', '^' -> {
                    edittext.text.replace(
                        start.coerceAtMost(end), start.coerceAtLeast(end),
                        "$number", 0, 1
                    )
                }
                else -> {
                    edittext.text.replace(
                        start.coerceAtMost(end), start.coerceAtLeast(end),
                        "×$number", 0, 2
                    )
                }
            }

        }
        else {
            edittext.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "$number", 0, 1
            )
        }
    }

    private fun numberEntry(start: Int, end: Int, number: Char){
        if (start != 0 && (edittext.text.toString()[start - 1] == '%' || edittext.text.toString()[start - 1] == ')'
                    || edittext.text.toString()[start - 1] == 'π' || edittext.text.toString()[start - 1] == 'e'
                    || edittext.text.toString()[start - 1] == '!'))
        {
            edittext.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "×$number", 0, 2
            )
        }
        else {
            edittext.text.replace(
                start.coerceAtMost(end), start.coerceAtLeast(end),
                "$number", 0, 1
            )
        }
    }

    private fun signInput(start: Int, end: Int, sign: Char){
        //Проверка что кусор в начале
        if (start == 0){
            //Ставим знак минус в начале
            if (sign == '-' && (edittext.text.isEmpty() || edittext.text.toString()[0] != '-' || start != end)){
                edittext.text.replace(
                    start.coerceAtMost(end), start.coerceAtLeast(end),
                    "$sign", 0, 1
                )
            }
            else {
                Toast.makeText(
                    activity?.applicationContext,
                    "Invalid format used",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        else {
            when (edittext.text.toString()[start - 1]){
                //Проверяем какой знак слева от курсора
                '-', '+', '×', '÷' -> {
                        //Если слева от начала курсора минус и он первый, а справа число
                        if((start == 1 || edittext.text.toString()[start - 2] == '(') && edittext.text.toString()[start - 1] == '-')
                        {
                            if (sign == '-')
                            {
                                val newStart = start - 1
                                edittext.text.replace(
                                    newStart.coerceAtMost(end), newStart.coerceAtLeast(end),
                                    "$sign", 0, 1
                                )
                            }
                            else {
                                Toast.makeText(
                                    activity?.applicationContext,
                                    "Invalid format used",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        //Меняем символ слева от начала курсора
                        else {
                            val newStart = start - 1
                            edittext.text.replace(
                                newStart.coerceAtMost(end), newStart.coerceAtLeast(end),
                                "$sign", 0, 1
                            )
                        }
                }
                '(' -> {
                    if (sign == '-')
                    {
                        if (end != edittext.text.length && edittext.text.toString()[end] == '-') {
                            edittext.text.replace(
                                start.coerceAtMost(end + 1), start.coerceAtLeast(end + 1),
                                "$sign", 0, 1
                            )
                        }
                        else {
                            edittext.text.replace(
                                start.coerceAtMost(end), start.coerceAtLeast(end),
                                "$sign", 0, 1
                            )
                        }
                    }
                    else {
                        Toast.makeText(activity?.applicationContext, "Invalid format used", Toast.LENGTH_SHORT).show()
                    }

                }
                //Если слева от начала курсора число
                else ->{
                    //Если конец курсора в конце и слева от начала курсора число
                    if (end == edittext.text.length){
                        edittext.text.replace(
                            start.coerceAtMost(end), start.coerceAtLeast(end),
                            "$sign", 0, 1
                        )
                    }
                    //Если конец курсора не в конце
                    else {

                        when (edittext.text.toString()[end]) {
                            //Если справа от конца курсора знак то меняем его
                            '-', '+', '×', '÷' -> {
                                val newEnd = end + 1

                                edittext.text.replace(
                                    start.coerceAtMost(newEnd), start.coerceAtLeast(newEnd),
                                    "$sign", 0, 1
                                )
                            }
                            '.' -> {
                                Toast.makeText(activity?.applicationContext, "Invalid format used", Toast.LENGTH_SHORT).show()
                            }
                            //Если справа от конца курсора числа просто все меняем
                            else -> {
                                edittext.text.replace(
                                    start.coerceAtMost(end), start.coerceAtLeast(end),
                                    "$sign", 0, 1
                                )
                            }
                        }
                    }
                }
            }

        }
    }

    fun setDescription(buttonIndex: Int) {
        if (edittext.isFocused) {
            val start = edittext.selectionStart.coerceAtLeast(0)
            val end = edittext.selectionEnd.coerceAtLeast(0)
            when (buttonIndex) {
                1 -> {
                    numberEntry(start, end, '1')

                }
                2 -> {
                    numberEntry(start, end, '2')

                }
                3 -> {
                    numberEntry(start, end, '3')

                }
                4 -> {
                    numberEntry(start, end, '4')
                }
                5 -> {
                    numberEntry(start, end, '5')
                }
                6 -> {
                    numberEntry(start, end, '6')
                }
                7 -> {
                    numberEntry(start, end, '7')
                }
                8 -> {
                    numberEntry(start, end, '8')
                }
                9 -> {
                    numberEntry(start, end, '9')
                }
                10 -> {
                    numberEntry(start, end, '0')
                }
                11 -> {
                    if (edittext.text.isNotEmpty())
                    {
                        var leftSide = start - 1
                        var rightSide = end
                        val text = edittext.text.toString()
                        if (leftSide >= 0)
                        {
                            while (text[leftSide] != '+' && text[leftSide] != '-' && text[leftSide] != '×' && text[leftSide] != '÷' && text[leftSide] != '%' && text[leftSide] != '(')
                            {
                                if(text[leftSide] == '.'){
                                    Toast.makeText(activity?.applicationContext, "Invalid format used", Toast.LENGTH_SHORT).show()
                                    return
                                }
                                leftSide--
                                if (leftSide < 0){
                                    break
                                }
                            }
                        }

                        if (rightSide != text.length) {
                            while (text[rightSide] != '+' && text[rightSide] != '-' && text[rightSide] != '×' && text[rightSide] != '÷' && text[rightSide] != '%' && text[rightSide] != '(') {
                                if (text[rightSide] == '.') {
                                    Toast.makeText(activity?.applicationContext, "Invalid format used", Toast.LENGTH_SHORT).show()
                                    return
                                }
                                rightSide++
                                if (rightSide == text.length) {
                                    break
                                }
                            }
                        }
                    }

                    if (edittext.text.toString() == ""){
                        edittext.text.replace(
                            start.coerceAtMost(end), start.coerceAtLeast(end),
                            "0.", 0, 2
                        )
                    }
                    else{
                        if (edittext.text.isNotEmpty() && start == 0){
                            edittext.text.replace(
                                start.coerceAtMost(end), start.coerceAtLeast(end),
                                "0.", 0, 2
                            )
                        }
                        else {
                            when (edittext.text.toString()[start - 1]) {
                                '-', '+', '×', '÷', '(', '√', '^' -> {
                                    edittext.text.replace(
                                        start.coerceAtMost(end), start.coerceAtLeast(end),
                                        "0.", 0, 2
                                    )
                                }
                                '%', ')', 'e', 'π', '!' -> {
                                    edittext.text.replace(
                                        start.coerceAtMost(end), start.coerceAtLeast(end),
                                        "×0.", 0, 3
                                    )
                                }
                                else -> {
                                    edittext.text.replace(
                                        start.coerceAtMost(end), start.coerceAtLeast(end),
                                        ".", 0, 1
                                    )
                                }
                            }
                        }
                    }
                }
                12 -> {
                    if (edittext.selectionEnd - edittext.selectionStart > 0) {

                        val selectedText = edittext.text.toString().substring(start, end)
                        val brackets = selectedText.count { it == '(' }
                        val closedBrackets = selectedText.count { it == ')' }
                        openBrackets -= brackets
                        openBrackets += closedBrackets
                        edittext.text.delete(edittext.selectionStart, edittext.selectionEnd)
                        if (edittext.text.isNotEmpty() && (edittext.text.toString()[0] == '.' || edittext.text.toString()[0] == '+' || edittext.text.toString()[0] == '×' ||
                            edittext.text.toString()[0] == '÷')){
                            edittext.text.delete(0, 1)
                        }
                    }
                    else {
                        if (edittext.selectionStart > 0)
                        {
                            if (edittext.text.toString()[start - 1] == '('){
                                openBrackets--
                            }
                            if (edittext.text.toString()[start - 1] == ')'){
                                openBrackets++
                            }
                            edittext.text.delete(edittext.selectionStart - 1, edittext.selectionStart)
                            if (edittext.text.isNotEmpty() && (edittext.text.toString()[0] == '.' || edittext.text.toString()[0] == '+' || edittext.text.toString()[0] == '×' ||
                                        edittext.text.toString()[0] == '÷')){
                                edittext.text.delete(0, 1)
                            }
                        }
                    }

                }
                13 -> {
                    signInput(start, end, '÷')
                }
                14 -> {
                    signInput(start, end, '×')
                }
                15 -> {
                    signInput(start, end, '-')
                }
                16 -> {
                    signInput(start, end, '+')
                }
                17 -> {
                    edittext.text.clear()
                    textView.text = ""
                    openBrackets = 0
                }
                18 -> {
                    if (start == 0){
                        openBrackets++
                        edittext.text.replace(
                            start.coerceAtMost(end), start.coerceAtLeast(end),
                            "(", 0, 1
                        )
                    }
                    else {
                        when (edittext.text.toString()[start - 1]) {
                            '-', '+', '×', '÷', '(', '√', '^' -> {
                                openBrackets++
                                edittext.text.replace(
                                    start.coerceAtMost(end), start.coerceAtLeast(end),
                                    "(", 0, 1
                                )
                            }
                            else -> {
                                if (end != edittext.text.length && edittext.text.toString()[end] == '.') {
                                    Toast.makeText(
                                        activity?.applicationContext,
                                        "Invalid format used",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else
                                {
                                    if (openBrackets == 0
                                        || edittext.text.toString().substring(0, start).count { it == ')' } >= edittext.text.toString().substring(0, start).count { it == '(' }){
                                    edittext.text.replace(
                                        start.coerceAtMost(end), start.coerceAtLeast(end),
                                        "×(", 0, 2
                                    )
                                    openBrackets++
                                    }
                                    else {
                                        if (openBrackets > 0){
                                            when (edittext.text.toString()[start - 1]){
                                                '-', '+', '×', '÷', '(' -> {
                                                }
                                                else -> {
                                                    if (end == edittext.text.length){
                                                        edittext.text.replace(
                                                            start.coerceAtMost(end),
                                                            start.coerceAtLeast(end),
                                                            ")",
                                                            0,
                                                            1
                                                        )
                                                    }
                                                    else {
                                                        when (edittext.text.toString()[end])
                                                        {
                                                            '-', '+', '×', '÷', ')', '%', '!' -> {
                                                                edittext.text.replace(
                                                                    start.coerceAtMost(end),
                                                                    start.coerceAtLeast(end),
                                                                    ")",
                                                                    0,
                                                                    1
                                                                )
                                                            }
                                                            else -> {
                                                                edittext.text.replace(
                                                                    start.coerceAtMost(end),
                                                                    start.coerceAtLeast(end),
                                                                    ")×",
                                                                    0,
                                                                    2
                                                                )
                                                            }
                                                        }
                                                    }
                                                    openBrackets--
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                19 -> {
                    if (start == 0){
                        Toast.makeText(activity?.applicationContext, "Invalid format used", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        when (edittext.text.toString()[start - 1]){
                            '-', '+', '×', '÷', '%', '(', '√', '^' -> {
                                Toast.makeText(activity?.applicationContext, "Invalid format used", Toast.LENGTH_SHORT).show()
                            }
                            else ->{
                                if (end == edittext.text.length)
                                {
                                    edittext.text.replace(
                                        start.coerceAtMost(end), start.coerceAtLeast(end),
                                        "%", 0, 1
                                    )
                                }
                                else {
                                    when (edittext.text.toString()[end]) {
                                        '-', '+', '×', '÷' -> {
                                            edittext.text.replace(
                                                start.coerceAtMost(end), start.coerceAtLeast(end),
                                                "%", 0, 1
                                            )

                                        }
                                        '%', '.' -> {
                                            Toast.makeText(activity?.applicationContext, "Invalid format used", Toast.LENGTH_SHORT).show()
                                        }
                                        else -> {
                                            edittext.text.replace(
                                                start.coerceAtMost(end), start.coerceAtLeast(end),
                                                "%", 0, 1
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                20 -> {
                    var text = edittext.text.toString()
                    var brackets = openBrackets
                    if (brackets > 0) {
                        while (brackets != 0){
                            text = text + ')'
                            brackets--
                        }
                    }
                    text = text.replace('×', '*')
                    text = text.replace('÷', '/')
                    val exp = Expression(text)
                    var result = exp.calculate().toString()
                    if (result != "NaN")
                    {
                        result = result.toBigDecimal().toPlainString()
                    }
                    textView.text = result

                }
                //Пустой
                21 -> {

                }
                22 -> {
                    if (start != 0 )
                    {
                        when (edittext.text.toString()[start - 1]){
                            '-', '+', '×', '÷', '(', '√', '^' -> {
                                edittext.text.replace(
                                    start.coerceAtMost(end), start.coerceAtLeast(end),
                                    "√(", 0, 2
                                )
                            }
                            else -> {
                                edittext.text.replace(
                                    start.coerceAtMost(end), start.coerceAtLeast(end),
                                    "×√(", 0, 3
                                )
                            }
                        }

                    }
                    else {
                        edittext.text.replace(
                            start.coerceAtMost(end), start.coerceAtLeast(end),
                            "√(", 0, 2
                        )
                    }
                    openBrackets++
                }
                23 -> {
                    constInput(start, end, 'π')
                }
                //Пустой
                24 -> {

                }
                25 -> {
                    if (start != 0 )
                    {
                        when (edittext.text.toString()[start - 1]){
                            '-', '+', '×', '÷', '(', '√', '^' -> {
                                Toast.makeText(activity?.applicationContext, "Invalid format used", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                edittext.text.replace(
                                    start.coerceAtMost(end), start.coerceAtLeast(end),
                                    "^(", 0, 2
                                )
                                openBrackets++
                            }
                        }

                    }
                    else {
                        Toast.makeText(activity?.applicationContext, "Invalid format used", Toast.LENGTH_SHORT).show()
                    }
                }
                26 -> {
                    if (start != 0 )
                    {
                        when (edittext.text.toString()[start - 1]){
                            '-', '+', '×', '÷', '(', '√', '^' -> {
                                Toast.makeText(activity?.applicationContext, "Invalid format used", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                edittext.text.replace(
                                    start.coerceAtMost(end), start.coerceAtLeast(end),
                                    "!", 0, 1
                                )
                            }
                        }

                    }
                    else {
                        Toast.makeText(activity?.applicationContext, "Invalid format used", Toast.LENGTH_SHORT).show()
                    }
                }
                27 -> {
                    edittext.text.replace(
                        start.coerceAtMost(end), start.coerceAtLeast(end),
                        "sin(", 0, 1
                    )
                    openBrackets++
                }
                28 -> {
                    edittext.text.replace(
                        start.coerceAtMost(end), start.coerceAtLeast(end),
                        "cos(", 0, 4
                    )
                    openBrackets++
                }
                29 -> {
                    edittext.text.replace(
                        start.coerceAtMost(end), start.coerceAtLeast(end),
                        "tan(", 0, 4
                    )
                    openBrackets++
                }
                30 -> {
                    constInput(start, end, 'e')
                }
                31 -> {
                    edittext.text.replace(
                        start.coerceAtMost(end), start.coerceAtLeast(end),
                        "ln(", 0, 1
                    )
                    openBrackets++
                }
                32 -> {
                    edittext.text.replace(
                        start.coerceAtMost(end), start.coerceAtLeast(end),
                        "log(", 0, 1
                    )
                    openBrackets++
                }
                else -> {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edittext = view.findViewById(R.id.editTextNumberDecimal)
        textView = view.findViewById(R.id.textView)

        edittext.showSoftInputOnFocus = false

        edittext.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                menu.clear()
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode) {
            }

            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                return true
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                return false
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