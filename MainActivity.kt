package com.mahad.madfalle

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var display_question:TextView
    private lateinit var display_ans:TextView

    private var text = StringBuilder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display_question = findViewById(R.id.display_screen_question)
        display_ans = findViewById(R.id.display_screen_ans)

        val buttons = listOf<Button>(
            findViewById(R.id.Button1),
            findViewById(R.id.Button2),
            findViewById(R.id.Button3),
            findViewById(R.id.button4),
            findViewById(R.id.Button5),
            findViewById(R.id.Button6),
            findViewById(R.id.Button7),
            findViewById(R.id.Button8),
            findViewById(R.id.Button9),
            findViewById(R.id.Button0),
            findViewById(R.id.ButtonAns),
            findViewById(R.id.Button_cl),
            findViewById(R.id.Button_plus),
            findViewById(R.id.Buttonminus),
            findViewById(R.id.Buttondivide),
            findViewById(R.id.Buttonmultiply),
            findViewById(R.id.Button_point)
        )
        buttons.forEach { button ->
            button.setOnClickListener {
                when (button) {
                    buttons[11] -> {
                        display_question.text = ""
                        display_ans.text = ""
                        text.delete(0, text.length)
                    }
                    buttons[16] -> {
                        try {
                            if (display_question.text.isNotEmpty()) {
                                text.deleteCharAt(text.length - 1)
                                display_question.text = text.toString()
                            } else{
                                Toast.makeText(applicationContext, "Nothing to clear", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(applicationContext, "An error occurred", Toast.LENGTH_SHORT).show()
                        }
                    }
                    buttons[10] -> {
                        calculate()
                    }
                    else -> {
                        handleButtonActions(button.text.toString())
                    }
                }
            }
        }
    }
    private fun changeOperators():String{
        var expression = text.replace(Regex("×"), "*")
        expression = expression.replace(Regex("÷"), "/")
        return expression
    }
    private fun handleButtonActions(digits: String){
        text.append(digits)
        display_question.text = text.toString()
    }
    fun calculate(){
        try {
            val expression = changeOperators()
            val result = Expression(expression).calculate()
            if(result.isNaN()){
                text.delete(0, text.length)
                text.append("Error")
            }
            else{
                display_ans.text = text.delete(0, text.length)
                display_ans.text = result.toString()
            }
        }
        catch(e: Exception){
            text.delete(0, text.length)
            text.append("Error!!!")
        }
    }
}
