package com.example.converter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OnSelectedButtonListener {


    override fun onButtonSelected(buttonIndex: Int) {
        val fragmentManager = supportFragmentManager

        // Получаем ссылку на второй фрагмент по ID
        val data_fragment = fragmentManager.findFragmentById(R.id.data_fragment) as DataFragment?
        data_fragment?.setDescription(buttonIndex)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val edit1Text = findViewById<EditText>(R.id.editTextNumberDecimal).text
        val edit2Text = findViewById<EditText>(R.id.editTextNumberDecimal2).text

        val spinnerId = findViewById<Spinner>(R.id.spinner4).selectedItemPosition
        val spinner2Id = findViewById<Spinner>(R.id.spinner).selectedItemPosition
        val spinner3Id = findViewById<Spinner>(R.id.spinner3).selectedItemPosition

        outState.putCharSequence("savedText", edit1Text)
        outState.putCharSequence("savedText2", edit2Text)

        outState.putInt("spinnerId", spinnerId)
        outState.putInt("spinner2Id", spinner2Id)
        outState.putInt("spinner3Id", spinner3Id)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val edit1Text = savedInstanceState.getCharSequence("savedText")
        findViewById<EditText>(R.id.editTextNumberDecimal).setText(edit1Text)
        val edit2Text = savedInstanceState.getCharSequence("savedText")
        findViewById<EditText>(R.id.editTextNumberDecimal2).setText(edit2Text)
        val spinnerId = savedInstanceState.getInt("spinnerId")
        findViewById<Spinner>(R.id.spinner4).setSelection(spinnerId)
        val spinner2Id = savedInstanceState.getInt("spinner2Id")
        findViewById<Spinner>(R.id.spinner).setSelection(2)
        val spinner3Id = savedInstanceState.getInt("spinner3Id")
        findViewById<Spinner>(R.id.spinner3).setSelection(2)
    }*/

}