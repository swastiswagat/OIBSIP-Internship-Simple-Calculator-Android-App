package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput = ""
    private var operator: Char? = null
    private var num1: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.textViewResult)
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val addButton: Button = findViewById(R.id.buttonPlus)
        val subtractButton: Button = findViewById(R.id.buttonMinus)
        val multiplyButton: Button = findViewById(R.id.buttonMultiply)
        val divideButton: Button = findViewById(R.id.buttonDivide)
        val equalButton: Button = findViewById(R.id.buttonEqual)
        val clearButton: Button = findViewById(R.id.buttonClear)
        val dotButton: Button = findViewById(R.id.buttonDot)
        button0.setOnClickListener { appendToInput("0") }
        button1.setOnClickListener { appendToInput("1") }
        button2.setOnClickListener { appendToInput("2") }
        button3.setOnClickListener { appendToInput("3") }
        button4.setOnClickListener { appendToInput("4") }
        button5.setOnClickListener { appendToInput("5") }
        button6.setOnClickListener { appendToInput("6") }
        button7.setOnClickListener { appendToInput("7") }
        button8.setOnClickListener { appendToInput("8") }
        button9.setOnClickListener { appendToInput("9") }
        dotButton.setOnClickListener { appendToInput(".") }
        addButton.setOnClickListener { setOperator('+') }
        subtractButton.setOnClickListener { setOperator('-') }
        multiplyButton.setOnClickListener { setOperator('*') }
        divideButton.setOnClickListener { setOperator('/') }
        equalButton.setOnClickListener { performCalculation() }
        clearButton.setOnClickListener { clearInput() }
    }

    @SuppressLint("SetTextI18n")
    private fun appendToInput(value: String) {
        currentInput += value
        resultTextView.text = currentInput
    }

    private fun setOperator(selectedOperator: Char) {
        if (currentInput.isNotEmpty()) {
            num1 = currentInput.toDoubleOrNull()
            if (num1 != null) {
                operator = selectedOperator
                currentInput += " $operator "
                resultTextView.text = currentInput
            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun performCalculation() {
        val inputParts = currentInput.split(" ")
        if (inputParts.size == 3) {
            val num2 = inputParts[2].toDoubleOrNull()
            if (num2 != null) {
                var result = 0.0
                when (operator) {
                    '+' -> result = num1!! + num2
                    '-' -> result = num1!! - num2
                    '*' -> result = num1!! * num2
                    '/' -> {
                        if (num2 != 0.0) {
                            result = num1!! / num2
                        } else {
                            Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                            return
                        }
                    }
                }
                val df = DecimalFormat("#.##")
                resultTextView.text = "$currentInput = ${df.format(result)}"
                clearInputAfterCalculation()
            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Invalid operation", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearInput() {
        currentInput = ""
        num1 = null
        operator = null
        resultTextView.text = "0"
    }

    private fun clearInputAfterCalculation() {
        currentInput = ""
        num1 = null
        operator = null
    }
}
