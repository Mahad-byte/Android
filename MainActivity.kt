package com.mahad.madfalle

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var result: TextView
    private lateinit var num1: EditText
    private lateinit var num2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        num1 = findViewById(R.id.number1)
        num2 = findViewById(R.id.number2)
        result = findViewById(R.id.result)

        val buttons = listOf<Button>(
            findViewById(R.id.plus),
            findViewById(R.id.minus),
            findViewById(R.id.divide),
            findViewById(R.id.multiply)
        )

//        val rnum1 = num1.text.toString().toInt()          App doesn't opens
//        val rrnum2 = num2.text.toString().toInt()

        buttons.forEach { button ->
            button.setOnClickListener {
                if (num1.text.toString().isNotEmpty() && num2.text.toString().isNotEmpty()) {
                    val rnum1 = num1.text.toString().toInt()
                    val rrnum2 = num2.text.toString().toInt()
                    try {
                        when (button) {
                            buttons[0] -> {

                                val rresult = rnum1 + rrnum2
                                result.text = rresult.toString()

                            }

                            buttons[1] -> {
                                val rresult: Int = rnum1 - rrnum2
                                result.text = rresult.toString()
                            }

                            buttons[2] -> {
                                val rresult: Int = rnum1 / rrnum2
                                result.text = rresult.toString()
                            }

                            buttons[3] -> {
                                val rresult: Int = rnum1 * rrnum2
                                result.text = rresult.toString()
                            }
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                else{
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
