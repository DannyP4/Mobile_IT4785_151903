package com.example.currencyconversion

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Spinner
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etFirstConversion: EditText
    private lateinit var etSecondConversion: EditText
    private lateinit var spinnerFirstConversion: Spinner
    private lateinit var spinnerSecondConversion: Spinner

    private val exchangeRates = mapOf(
        Pair("USD", "EUR") to 0.85f,
        Pair("USD", "VND") to 23000f,
        Pair("EUR", "USD") to 1.18f,
        Pair("EUR", "VND") to 27000f,
        Pair("VND", "USD") to 0.000043f,
        Pair("VND", "EUR") to 0.000037f,
        Pair("VND", "JPY") to 0.0061f,
        Pair("JPY", "VND") to 165.16f,
        Pair("USD", "JPY") to 153.24f,
        Pair("JPY", "USD") to 0.0065f,
        Pair("VND", "GBP") to 0.000030f,
        Pair("GBP", "VND") to 32.904f,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFirstConversion = findViewById(R.id.et_firstConversion)
        etSecondConversion = findViewById(R.id.et_secondConversion)
        spinnerFirstConversion = findViewById(R.id.spinner_firstConversion)
        spinnerSecondConversion = findViewById(R.id.spinner_secondConversion)

        setupSpinners()
        setupTextWatcher()
    }

    private fun setupSpinners() {
        val currencies = arrayOf("USD", "EUR", "VND", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD", "KRW", "SGD", "NOK", "MXN", "INR", "RUB", "ZAR", "TRY", "BRL", "HKD", "IDR", "MYR", "PHP", "DKK", "CZK", "HUF", "THB", "PLN")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFirstConversion.adapter = adapter
        spinnerSecondConversion.adapter = adapter
    }

    private fun setupTextWatcher() {
        etFirstConversion.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateConversionResult()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun updateConversionResult() {
        val fromCurrency = spinnerFirstConversion.selectedItem.toString()
        val toCurrency = spinnerSecondConversion.selectedItem.toString()
        val amountText = etFirstConversion.text.toString()

        if (amountText.isNotEmpty()) {
            val amount = amountText.toFloatOrNull()
            if (amount != null) {
                val rate = exchangeRates[Pair(fromCurrency, toCurrency)] ?: 1.0f
                val result = amount * rate

                etSecondConversion.setText(result.toString())
            }
        } else {
            etSecondConversion.setText("")
        }
    }
}
