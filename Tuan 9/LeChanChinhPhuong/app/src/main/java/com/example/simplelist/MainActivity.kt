package com.example.simplelist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var etFirstConversion: EditText
    private lateinit var button: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<Int>
    val evenNumbers = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFirstConversion = findViewById(R.id.editTextText2)
        button = findViewById(R.id.button)
        radioGroup = findViewById(R.id.radioGroup)
        listView = findViewById(R.id.listView)

        // Thiết lập adapter cho ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, evenNumbers)
        listView.adapter = adapter

        // Sự kiện click cho Button
        button.setOnClickListener {
            calculate()
        }

        setupTextWatcher()
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
        // Thêm các xử lý khác nếu cần
    }

    private fun calculate() {
        // Xóa danh sách trước khi thêm dữ liệu mới
        evenNumbers.clear()

        // Lấy giá trị từ EditText và chuyển thành số nguyên
        val input = etFirstConversion.text.toString()
        val number = input.toIntOrNull()

        if (number == null || number < 0) {
            etFirstConversion.setText("invalid")
        } else {
            when (radioGroup.checkedRadioButtonId) {
                R.id.radioButton4 -> {  // Số chẵn
                    for (i in 1..number) {
                        if (i % 2 == 0) evenNumbers.add(i)
                    }
                }
                R.id.radioButton5 -> {  // Số lẻ
                    for (i in 1..number) {
                        if (i % 2 == 1) evenNumbers.add(i)
                    }
                }
                R.id.radioButton6 -> {  // Số chính phương
                    for (i in 1..sqrt(number.toDouble()).toInt()) {
                        evenNumbers.add(i * i)
                    }
                }
            }
            // Cập nhật ListView
            adapter.notifyDataSetChanged()
        }
    }
}
