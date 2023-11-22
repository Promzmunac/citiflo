package com.munachi.citiflo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.munachi.citiflo.navfragments.FavouriteFragment
import com.munachi.citiflo.navfragments.HomeFragment
import com.munachi.citiflo.navfragments.PaymentFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.munachi.citiflo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var bottomNv: BottomNavigationView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        // Change the color of the status bar
        window.statusBarColor = ContextCompat.getColor(this, R.color.HomeBg)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.HomeBg)

        loadFragment(HomeFragment())

        bottomNv = binding.bottomNavBar
        bottomNv.background = null
        bottomNv.menu.getItem(2).isEnabled = false


        bottomNv.setOnItemSelectedListener { item ->

            when(item.itemId){
                R.id.homeFragment -> {
                    loadFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.favouriteFragment -> {
                    loadFragment(FavouriteFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.paymentFragment -> {
                    loadFragment(PaymentFragment())
                    return@setOnItemSelectedListener true
                }

              /*  R.id.fragment_profile -> {
                    loadFragment(ProfileFragment())
                    return@setOnItemSelectedListener true
                }
*/
                else -> return@setOnItemSelectedListener false
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().also {
                fragmentTransaction ->
            fragmentTransaction.replace(R.id.framelayout,fragment)
            fragmentTransaction.commit()
        }
    }
}
