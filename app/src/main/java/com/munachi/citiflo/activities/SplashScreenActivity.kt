package com.munachi.citiflo.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.munachi.citiflo.R
import com.munachi.citiflo.databinding.ActivityMainBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onStart() {
        super.onStart()
        Splash()
    }

    @SuppressLint("ResourceAsColor")
    fun Splash(){
       // window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.statusBarColor = ContextCompat.getColor(this, R.color.md_light_green_600)

        Handler().postDelayed(
            {
                val intent = Intent(this, OnboardingActivity::class.java)
                finish()
                startActivity(intent)
            }, 3000
        )

        //loading my custom made animations
        val animation = AnimationUtils.loadAnimation( this , R.anim.fade_in)

        //starting the animation
        binding.splashFragment.startAnimation(animation)


    }

}

/*
private fun Animation() {
    val anim = ValueAnimator.ofFloat(0f, 1f)
    anim.duration = 2000

    val hsv = FloatArray(3) // Transition color
    hsv[1] = 1f
    hsv[2] = 1f

    anim.addUpdateListener { animation ->
        hsv[0] = 360 * animation.animatedFraction

        val runColor = Color.HSVToColor(hsv)
        binding.linearLayoutOne.setBackgroundColor(runColor)
    }

    anim.repeatCount = ValueAnimator.INFINITE

    anim.start()

}*/
