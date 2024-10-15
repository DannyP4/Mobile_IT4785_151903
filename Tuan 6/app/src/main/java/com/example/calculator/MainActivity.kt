package com.example.calculator

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: EditText
    private var operator = ""
    private var firstValue = 0.0
    private var secondValue = 0.0
    private var isNewOp = true
    private var hasDecimal = false // check if decimal number

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.editTextNumber)

        val numberButtons = arrayOf(
            findViewById<Button>(R.id.button7),  // 7
            findViewById<Button>(R.id.button8),  // 8
            findViewById<Button>(R.id.button9),  // 9
            findViewById<Button>(R.id.button11), // 4
            findViewById<Button>(R.id.button12), // 5
            findViewById<Button>(R.id.button13), // 6
            findViewById<Button>(R.id.button15), // 1
            findViewById<Button>(R.id.button16), // 2
            findViewById<Button>(R.id.button17), // 3
            findViewById<Button>(R.id.button20)  // 0
            //findViewById<Button>(R.id.button21)
        )

        numberButtons.forEach { button ->
            button.setOnClickListener { appendNumber(button.text.toString()) }
        }

        findViewById<Button>(R.id.button18).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.button14).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.button10).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.button6).setOnClickListener { setOperator("/") }

        findViewById<Button>(R.id.button22).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.button3).setOnClickListener { clearEntry() }
        findViewById<Button>(R.id.button4).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.button21).setOnClickListener { appendDecimal() }
    }

    private fun appendNumber(number: String) {
        if (isNewOp) {
            display.setText(number)
            isNewOp = false
        } else
            display.append(number)
    }

    private fun appendDecimal() {
        val currentText = display.text.toString()
        if (!currentText.contains(".")) {
            if (isNewOp)
                display.setText("0.")
            else
                display.append(".")

            isNewOp = false
            hasDecimal = true
        }
    }

    private fun setOperator(op: String) {
        if (!TextUtils.isEmpty(display.text)) {
            firstValue = display.text.toString().toDouble()
            operator = op
            isNewOp = true
            hasDecimal = false
        }
    }

    private fun calculateResult() {
        if (!TextUtils.isEmpty(display.text)) {
            if (operator.isEmpty()) {
                firstValue = display.text.toString().toDouble()
                display.setText(firstValue.toString())
            } else {
                secondValue = display.text.toString().toDouble()
                var result = 0.0

                when (operator) {
                    "+" -> result = firstValue + secondValue
                    "-" -> result = firstValue - secondValue
                    "*" -> result = firstValue * secondValue
                    "/" -> result = if (secondValue != 0.0) firstValue / secondValue else Double.NaN
                }

                val formattedResult = if (result == result.toInt().toDouble())
                    result.toInt().toString()
                else
                    String.format("%.3f", result)

                display.setText(formattedResult)
            }

            isNewOp = true
            hasDecimal = false
        }
    }


    private fun clearEntry() {
        display.setText("0")
        isNewOp = true
        hasDecimal = false
    }

    private fun clearAll() {
        display.setText("0")
        firstValue = 0.0
        secondValue = 0.0
        operator = ""
        isNewOp = true
        hasDecimal = false
    }
}
