package com.munachi.citiflo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.munachi.citiflo.navfragments.HomeFragment
import com.munachi.citiflo.navfragments.MessageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
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

        // Change the color of the status bar
        window.statusBarColor = ContextCompat.getColor(this, R.color.HomeBg)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.HomeBg)

        setContentView(binding.root)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()


        loadHomeFragment()
//
        bottomNv = binding.bottomNavBar
        bottomNv.background = null
        bottomNv.menu.getItem(2).isEnabled = false


        bottomNv.setOnItemSelectedListener { item ->

            when(item.itemId){
                R.id.homeFragment -> {
                    loadFragment(HomeFragment())
                    window.statusBarColor = ContextCompat.getColor(this, R.color.md_green_900)

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

        window.statusBarColor = ContextCompat.getColor(this, R.color.HomeBg)

    }
}
