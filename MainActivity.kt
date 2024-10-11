package com.example.bookingapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookingapp.databinding.ActivityMainBinding
import com.example.bookingapp.fragments.AddHotelFragment
import com.example.bookingapp.fragments.FavoriteFragment
import com.example.bookingapp.fragments.HomeFragment
import com.example.bookingapp.fragments.MyHotelFragment
import com.example.bookingapp.fragments.ReservationsFragment
import com.example.bookingapp.screens.AuthHolderActivity
import com.example.bookingapp.screens.GetStartedActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        if(savedInstanceState == null){
            mainBinding.mainFragmentHolder.let {
                val rvFrag = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .add(it.id, rvFrag)
                    .commit()
            }
        }
        mainBinding.bottomBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.reservation -> {
                    val reservation = ReservationsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment_holder, reservation)
                        .commit()
                    true
                }
                R.id.home -> {
                    val homeFrag = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment_holder, homeFrag)
                        .commit()
                    true
                }
                R.id.favorite -> {
                    val favoriteFrag = FavoriteFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment_holder, favoriteFrag)
                        .commit()
                    true
                }
                R.id.my_hotel -> {
                    val myHotel = MyHotelFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment_holder, myHotel)
                        .commit()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
        val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)

        if(isFirstRun){
            val intent = Intent(this, GetStartedActivity::class.java)
            startActivity(intent)
        } else {
            /*
            Since Back Stack is going null, this else is called as main activity is still there / On launcher.
             */
            val currentUser = auth.currentUser
            if (currentUser == null) {
                val intentAuth = Intent(this, AuthHolderActivity::class.java)
                startActivity(intentAuth)
            }
        }

        //TODO
        mainBinding.addHotels.setOnClickListener {
            val addHotel = AddHotelFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment_holder, addHotel)
                .commit()
        }

    }

//    private fun loadFragment(fragment: Fragment) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.main_fragment_holder, fragment)
//            .commit()
//    }

}