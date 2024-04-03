package com.adso.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adso.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var operand1 = ""
    private var operand2 = ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberButtons = arrayOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9
        )


        val operationButtons = arrayOf(
                binding.btnAdd, binding.btnSubtract, binding.btnMultiply, binding.btnDivide, binding.btnEquals
        )

        val btnDelete = binding.btnDelete
        btnDelete.setOnClickListener {
            onDeleteButtonClick()
        }



        for (button in numberButtons) {
            button.setOnClickListener {
                onNumberButtonClick(button)
            }
        }

        for (button in operationButtons) {
            button.setOnClickListener {
                onOperationButtonClick(button)
            }
        }
    }
    private fun onDeleteButtonClick() {
        if (operator.isEmpty()) {
            // Si no hay operador, eliminamos del primer operando
            if (operand1.isNotEmpty()) {
                operand1 = operand1.substring(0, operand1.length - 1)
                binding.tvResult.text = operand1
            }
        } else {
            // Si hay operador, eliminamos del segundo operando
            if (operand2.isNotEmpty()) {
                operand2 = operand2.substring(0, operand2.length - 1)
                binding.tvResult.text = operand2
            }
        }
    }



    private fun onNumberButtonClick(button: Button) {
        val buttonText = button.text.toString()
        if (operator.isEmpty()) {
            operand1 += buttonText
            binding.tvResult.text = operand1
        } else {
            operand2 += buttonText
            binding.tvResult.text = operand2
        }
    }


    private fun onOperationButtonClick(button: Button) {
        val buttonText = button.text.toString()

        when (buttonText) {
            "+" -> operator = "+"
            "-" -> operator = "-"
            "*" -> operator = "*"
            "/" -> operator = "/"
            "=" -> {
                val result = calculateResult()
                binding.tvResult.text = result
                operand1 = result
                operand2 = ""
                operator = ""
            }
        }
    }


    private fun calculateResult(): String {
        val num1 = operand1.toDouble()
        val num2 = operand2.toDouble()
        return when (operator) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "*" -> (num1 * num2).toString()
            "/" -> if (num2 != 0.0) (num1 / num2).toString() else "Error"
            else -> "Error"
        }
    }


    private fun enableEdgeToEdge() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }
}