package com.adiaz.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var screenInput: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenInput = findViewById(R.id.screen)
    }

    fun onDigit(view: View) {
        screenInput?.append((view as Button).text)
    }

    fun onClear(view: View) {
        screenInput?.text = ""
    }

}