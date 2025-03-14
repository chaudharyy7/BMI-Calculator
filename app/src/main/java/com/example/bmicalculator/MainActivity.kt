package com.example.bmicalculator

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var isMaleSelected = false
    private var isFemaleSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ageEdt = findViewById<EditText>(R.id.Age_Edit)
        val heightEdt = findViewById<EditText>(R.id.Height_Edit)
        val weightEdt = findViewById<EditText>(R.id.Weight_Edit)
        val calculateBtn = findViewById<Button>(R.id.Calculate_Btn)
        val bmiResultTxt = findViewById<TextView>(R.id.BMI_Show)
        val conditionTxt = findViewById<TextView>(R.id.Condition)

        // Calculate BMI
        calculateBtn.setOnClickListener {
            val ageStr = ageEdt.text.toString()
            val heightStr = heightEdt.text.toString()
            val weightStr = weightEdt.text.toString()

            if (ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
                Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = ageStr.toIntOrNull()
            val height = heightStr.toFloatOrNull()
            val weight = weightStr.toFloatOrNull()

            if (age == null || height == null || weight == null || height <= 0 || weight <= 0) {
                Toast.makeText(this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val bmi = weight / ((height / 100) * (height / 100))
            bmiResultTxt.text = String.format("%.2f", bmi)

            // Set condition based on BMI
            val conditionText = when {
                bmi < 18.5 -> "Underweight"
                bmi in 18.5..24.9 -> "Normal"
                bmi in 25.0..29.9 -> "Overweight"
                else -> "Obese"
            }

            conditionTxt.text = conditionText
        }
    }
}