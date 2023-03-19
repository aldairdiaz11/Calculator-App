package com.adiaz.calculatorapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var screenInput: TextView? = null
    private var lastNumeric: Boolean = false
    private var lastDecPoint: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenInput = findViewById(R.id.screen)
    }

    fun onDigit(view: View) {
        screenInput?.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        screenInput?.text = ""
        lastDecPoint = false
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDecPoint) {
            screenInput?.append(".")
            lastNumeric = false
            lastDecPoint = true
        }
    }

    fun onOperator(view: View) {
        screenInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                // Operations:
                screenInput?.append((view as Button).text)
                lastNumeric = false
                lastDecPoint = false
            }
        }

    }

    @SuppressLint("SetTextI18n")
    fun onEqual(view: View) {
        if(lastNumeric) {
            var screenValue = screenInput?.text.toString()
            var prefix = ""

            try{
                if(screenValue.startsWith("-")) {
                    prefix = "-"
                    screenValue = screenValue.substring(1)
                }
                if(screenValue.contains("-")) {
                    val splitValue = screenValue.split("-")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    screenInput?.text = removeZeroAfterDot(
                        (one.toDouble() - two.toDouble()).toString())
                } else if(screenValue.contains("+")) {
                    val splitValue = screenValue.split("+")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    screenInput?.text = removeZeroAfterDot(
                        (one.toDouble() + two.toDouble()).toString())
                } else if(screenValue.contains("*")) {
                    val splitValue = screenValue.split("*")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    screenInput?.text = removeZeroAfterDot(
                        (one.toDouble() * two.toDouble()).toString())
                } else if(screenValue.contains("/")) {
                    val splitValue = screenValue.split("/")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    screenInput?.text = removeZeroAfterDot(
                        (one.toDouble() / two.toDouble()).toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String{
        var value = result
        if(result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }

    private fun isOperatorAdded(value: String): Boolean {

        return if(value.startsWith("-")) {
            false
        }else {
            value.contains("/") || value.contains("*") || value.contains("+") ||
                    value.contains("-")
        }
    }
}