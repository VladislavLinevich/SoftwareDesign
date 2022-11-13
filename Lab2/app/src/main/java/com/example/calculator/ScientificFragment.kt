package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScientificFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScientificFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private fun translateIdToIndex(id: Int): Int {
        var index = -1
        when (id) {
            R.id.button1 -> index = 1
            R.id.button2 -> index = 2
            R.id.button3 -> index = 3
            R.id.button4 -> index = 4
            R.id.button5 -> index = 5
            R.id.button6 -> index = 6
            R.id.button7 -> index = 7
            R.id.button8 -> index = 8
            R.id.button9 -> index = 9
            R.id.button10 -> index = 10
            R.id.button11 -> index = 11
            R.id.button12 -> index = 12
            R.id.button13 -> index = 13
            R.id.button14 -> index = 14
            R.id.button15 -> index = 15
            R.id.button16 -> index = 16
            R.id.button17 -> index = 17
            R.id.button18 -> index = 18
            R.id.button19 -> index = 19
            R.id.button20 -> index = 20
            R.id.button21 -> index = 21
            R.id.button22 -> index = 22
            R.id.button23 -> index = 23
            R.id.button24 -> index = 24
            R.id.button25 -> index = 25
            R.id.button26 -> index = 26
            R.id.button27 -> index = 27
            R.id.button28 -> index = 28
            R.id.button29 -> index = 29
            R.id.button30 -> index = 30
            R.id.button31 -> index = 31
            R.id.button32 -> index = 32

        }
        return index
    }

    override fun onClick(v: View?) {
        val buttonIndex = translateIdToIndex(v!!.id)

        val listener = activity as OnSelectedButtonListener?
        listener?.onButtonSelected(buttonIndex)
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
        return inflater.inflate(R.layout.fragment_scientific, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button1: Button = view.findViewById(R.id.button1)
        val button2: Button = view.findViewById(R.id.button2)
        val button3: Button = view.findViewById(R.id.button3)
        val button4: Button = view.findViewById(R.id.button4)
        val button5: Button = view.findViewById(R.id.button5)
        val button6: Button = view.findViewById(R.id.button6)
        val button7: Button = view.findViewById(R.id.button7)
        val button8: Button = view.findViewById(R.id.button8)
        val button9: Button = view.findViewById(R.id.button9)
        val button10: Button = view.findViewById(R.id.button10)
        val button11: Button = view.findViewById(R.id.button11)
        val button12: Button = view.findViewById(R.id.button12)
        val button13: Button = view.findViewById(R.id.button13)
        val button14: Button = view.findViewById(R.id.button14)
        val button15: Button = view.findViewById(R.id.button15)
        val button16: Button = view.findViewById(R.id.button16)
        val button17: Button = view.findViewById(R.id.button17)
        val button18: Button = view.findViewById(R.id.button18)
        val button19: Button = view.findViewById(R.id.button19)
        val button20: Button = view.findViewById(R.id.button20)
        val button21: Button = view.findViewById(R.id.button21)
        val button22: Button = view.findViewById(R.id.button22)
        val button23: Button = view.findViewById(R.id.button23)
        val button24: Button = view.findViewById(R.id.button24)
        val button25: Button = view.findViewById(R.id.button25)
        val button26: Button = view.findViewById(R.id.button26)
        val button27: Button = view.findViewById(R.id.button27)
        val button28: Button = view.findViewById(R.id.button28)
        val button29: Button = view.findViewById(R.id.button29)
        val button30: Button = view.findViewById(R.id.button30)
        val button31: Button = view.findViewById(R.id.button31)
        val button32: Button = view.findViewById(R.id.button32)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        button10.setOnClickListener(this)
        button11.setOnClickListener(this)
        button12.setOnClickListener(this)
        button13.setOnClickListener(this)
        button14.setOnClickListener(this)
        button15.setOnClickListener(this)
        button16.setOnClickListener(this)
        button17.setOnClickListener(this)
        button18.setOnClickListener(this)
        button19.setOnClickListener(this)
        button20.setOnClickListener(this)
        button21.setOnClickListener(this)
        button22.setOnClickListener(this)
        button23.setOnClickListener(this)
        button24.setOnClickListener(this)
        button25.setOnClickListener(this)
        button26.setOnClickListener(this)
        button27.setOnClickListener(this)
        button28.setOnClickListener(this)
        button29.setOnClickListener(this)
        button30.setOnClickListener(this)
        button31.setOnClickListener(this)
        button32.setOnClickListener(this)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScientificFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScientificFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}