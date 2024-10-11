package com.example.bookingapp.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookingapp.databinding.ActivityGetStartedBinding

class GetStartedActivity : AppCompatActivity() {
    private lateinit var getStartedBinding: ActivityGetStartedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getStartedBinding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(getStartedBinding.root)
        val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        getStartedBinding.getStartedButton.setOnClickListener {
            editor.putBoolean("isFirstRun", false)
            editor.apply()
            val intent = Intent(this, AuthHolderActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}