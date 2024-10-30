package com.example.listnumber

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private var isOdd: Boolean = false
    private var isEven: Boolean = false
    private var isSquare: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val showButton = findViewById<Button>(R.id.ShowButton)
        val input = findViewById<EditText>(R.id.InputNumber)
        val error = findViewById<TextView>(R.id.Error)
        error.text = ""
        val radioGroup = findViewById<RadioGroup>(R.id.AttibuteGroup)
        val recyclerView = findViewById<RecyclerView>(R.id.ShowNumber)

        recyclerView.layoutManager = LinearLayoutManager(this)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            isOdd = checkedId == R.id.Odd
            isEven = checkedId == R.id.Even
            isSquare = checkedId == R.id.Square
        }

        showButton.setOnClickListener {
            val maxNumber = input.text.toString().toIntOrNull()
            if (maxNumber == null || maxNumber < 0) {
                error.text = "Error"
                return@setOnClickListener
            }

            val numbers = mutableListOf<Int>()
            if (isOdd) {
                for (i in 1..maxNumber step 2) {
                    numbers.add(i)
                }
            }
            if (isEven) {
                for (i in 0..maxNumber step 2) {
                    numbers.add(i)
                }
            }
            if (isSquare) {
                val limit = sqrt(maxNumber.toDouble()).toInt()
                for (i in 1..limit) {
                    numbers.add(i * i)
                }
            }

            // Pass the list of numbers to the RecyclerView adapter
            recyclerView.adapter = NumberAdapter(numbers)
            error.text = "" // Clear any previous error message
        }
    }
}
