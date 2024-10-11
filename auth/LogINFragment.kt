package com.example.bookingapp.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookingapp.MainActivity
import com.example.bookingapp.databinding.FragmentLogINBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LogINFragment : Fragment() {
   private lateinit var logINBinding: FragmentLogINBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         logINBinding = FragmentLogINBinding.inflate(inflater, container, false)
        return logINBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logINBinding.LoginButton.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            startActivity(intent)
        }
    }

    /*
    removes all the entries up to SignUPFragment, since I created sign up in holder so sign up is the first fragment
    -> parentFragmentManager.popBackStack("SignUPFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)

    The latest state / Top of the stack in LgINFragment, so popping it will not do anything
    -> parentFragmentManager.popBackStack()
     */
}