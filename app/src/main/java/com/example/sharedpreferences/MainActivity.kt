package com.example.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText = findViewById<EditText>(R.id.editWeight)
        val heightText = findViewById<EditText>(R.id.editHeight)
        val calculateButton = findViewById<Button>(R.id.btnCalculate)

        calculateButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateUserInput(weight,height)){
                val bmi = weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
                //bmi to 2 decimal place
                val bmi2Decimal = String.format("%.2f",bmi).toFloat()
                displayResult(bmi2Decimal)
            }

        }
    }

    private fun validateUserInput(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"Weight is empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"Height is blank",Toast.LENGTH_LONG).show()
                return false
            }else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi: Float) {
        val resultIndex = findViewById<TextView>(R.id.textVIndex)
        val resultDescription = findViewById<TextView>(R.id.textVResult)
        val info = findViewById<TextView>(R.id.textVInfo)

        resultIndex.text = bmi.toString()
        info.text = "(Normal range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0;

        when{
            bmi<18.50 ->{
                resultText = "UnderWeight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText = "Overweight"
                color = R.color.over_weight
            }
            bmi > 29.99 ->{
                resultText = "Obese"
                color = R.color.obese
            }
        }

        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }


}