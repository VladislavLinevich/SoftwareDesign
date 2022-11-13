package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
}