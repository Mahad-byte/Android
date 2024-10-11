package com.example.bookingapp.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bookingapp.MainActivity
import com.example.bookingapp.R
import com.example.bookingapp.databinding.FragmentSignUPBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignUPFragment : Fragment() {
    private lateinit var signUPBinding: FragmentSignUPBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signUPBinding =  FragmentSignUPBinding.inflate(inflater, container, false)
        return signUPBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = FirebaseAuth.getInstance()
        signUPBinding.gotoMain.setOnClickListener {
            val email = signUPBinding.emailText.text.toString()
            val password = signUPBinding.passwordText.text.toString()
            val confirmPass = signUPBinding.passConfirmText.text.toString()

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()){ task ->
                if(task.isSuccessful){
                    val intentToMain = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intentToMain)
                }
                else{
                    Toast.makeText(requireContext(), "Failed to Sign in!", Toast.LENGTH_SHORT).show()
                    Log.d("Firebase Auth", "${task.exception}")
                }
            }
        }


        signUPBinding.gotoLogin.setOnClickListener {
            val login = LogINFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.holder_fragment, login)
                .addToBackStack("LogINFragment")
                .commit()
        }
    }

    private fun updateUI(user: FirebaseUser?, context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String, ) =
            SignUPFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}