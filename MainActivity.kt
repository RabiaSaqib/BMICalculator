package com.example.bmicalculator

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        status.visibility = View.GONE

        ReCalculate.visibility = View.GONE
        val myDialoug = Dialog(this)
        myDialoug.setContentView(R.layout.popup)
        myDialoug.setCancelable(true)
        myDialoug.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialoug.show()
        calculate_btn.setOnClickListener {

            // Check if the height EditText and Weight EditText are not empty
            if (etHeight.text.isNotEmpty() && etWeight.text.isNotEmpty()) {
                val height = (etHeight.text.toString()).toInt()
                val weight = (etWeight.text.toString()).toInt()

                // calculateBMI will return BMI
                val BMI = calculateBMI(height, weight)

                // update the status text as per the bmi conditions
                if (BMI < 18.5) {
                    status.text = "The BMI is: 'Under Weight'"
                } else if (BMI >= 18.5 && BMI < 24.9) {
                    status.text = "The BMI is: 'Healthy'"
                } else if (BMI >= 24.9 && BMI < 30) {
                    status.text = "The BMI is: 'Overweight'"
                } else if (BMI >= 30) {
                    status.text = "The BMI is: 'Suffering from Obesity'"
                }

                status.visibility = View.VISIBLE

                ReCalculate.visibility = View.VISIBLE
                calculate_btn.visibility = View.GONE

            }

            // when either Weight EditText or
            // height EditText have null value
            // we will display toast.
            else {
                Toast.makeText(this, "please enter the valid height and weight", Toast.LENGTH_SHORT).show()
            }
        }

        ReCalculate.setOnClickListener {
            ResetEverything()
        }
    }
    // Function to reset all Text and EditText fields.
    private fun ResetEverything() {

        calculate_btn.visibility = View.VISIBLE
        ReCalculate.visibility = View.GONE

        etHeight.text.clear()
        etWeight.text.clear()
        status.text = " "
    }

    // Function for calculating BMI
    private fun calculateBMI(height: Int, weight: Int): Float {

        val Height_in_metre = height.toFloat() / 100
        val BMI = weight.toFloat() / (Height_in_metre * Height_in_metre)

        return BMI
    }
}
