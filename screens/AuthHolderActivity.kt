package com.example.bookingapp.screens

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.bookingapp.databinding.ActivityAuthHolderBinding
import com.example.bookingapp.auth.SignUPFragment

class AuthHolderActivity : AppCompatActivity() {
    private lateinit var authHolderBinding: ActivityAuthHolderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authHolderBinding = ActivityAuthHolderBinding.inflate(layoutInflater)
        setContentView(authHolderBinding.root)

        if(savedInstanceState == null){
            authHolderBinding.holderFragment.let {
                val signUp = SignUPFragment()
                supportFragmentManager.beginTransaction().add(it.id, signUp).addToBackStack("SignUPFragment").commit()
            }
        }
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }


}