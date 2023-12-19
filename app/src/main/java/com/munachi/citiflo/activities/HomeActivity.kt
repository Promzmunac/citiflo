package com.munachi.citiflo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.munachi.citiflo.navfragments.HomeFragment
import com.munachi.citiflo.navfragments.MessageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.munachi.citiflo.R
import com.munachi.citiflo.databinding.ActivityHomeBinding
import com.munachi.citiflo.navfragments.AccountsFragment
import com.munachi.citiflo.navfragments.SaveFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var bottomNv: BottomNavigationView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getColor(R.color.md_green_900) // Use your color resource here
        window.navigationBarColor = ContextCompat.getColor(this, R.color.md_green_900)

        setContentView(binding.root)

        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()


        loadHomeFragment()

        bottomNv = binding.bottomNavBar
        bottomNv.background = null
        bottomNv.menu.getItem(2).isEnabled = false


        bottomNv.setOnItemSelectedListener { item ->

            when(item.itemId){
                R.id.homeFragment -> {
                    loadFragment(HomeFragment())

                    return@setOnItemSelectedListener true
                }

                R.id.messageFragment -> {
                    loadFragment(MessageFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.saveFragment -> {
                    loadFragment(SaveFragment())

                    return@setOnItemSelectedListener true
                }

                R.id.accountsFragment -> {
                    loadFragment(AccountsFragment())
                    return@setOnItemSelectedListener true
                }


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

    private fun loadHomeFragment(){
        loadFragment(HomeFragment())

        window.statusBarColor = ContextCompat.getColor(this, R.color.md_green_900)

    }
}
