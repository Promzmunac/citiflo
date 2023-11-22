package com.munachi.citiflo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.munachi.citiflo.adapter.OnboardingViewPagerAdapter
import com.munachi.citiflo.auth.LoginFragment
import com.munachi.citiflo.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var mViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)


        setContentView(binding.root)
        updateUi()
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.md_light_green_100)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.grayishWhite)


    }




    private fun updateUi(){

        //initialise the viewPager2
        mViewPager = binding.viewPager
        mViewPager.adapter = OnboardingViewPagerAdapter(this, this)
        mViewPager.offscreenPageLimit = 1

        mViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                if (position == 1) {
                    binding.btnNextStep.visibility = View.GONE

                } else {

                    binding.btnNextStep.visibility = View.VISIBLE
                }
            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(arg0: Int) {}
        })

        TabLayoutMediator(binding.pageIndicator, mViewPager) { _, _ -> }.attach()

        binding.btnNextStep.setOnClickListener {
            if (getItem() > mViewPager.childCount) {

                val intent = Intent(this, AuthenticationActivity::class.java)
                finish()
                startActivity(intent)

            } else {
                mViewPager.setCurrentItem(getItem() + 1, true)
            }
        }

        /* binding.btnPreviousStep.setOnClickListener {
             if (getItem() == 0) {
                 finish()
             } else {
                 mViewPager.setCurrentItem(getItem() - 1, true)
             }
         }*/

        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.textView3.setOnClickListener {
            loadFragment(LoginFragment())
            finish()
        }

        val appendText = " Login"
        appendColoredText(appendText, Color.GREEN)
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.rl_create_account,fragment)
            fragmentTransaction.commit()
        }
    }

    private fun appendColoredText(text: String, color: Int) {
        // Create a SpannableStringBuilder to hold the text and style
        val builder = SpannableStringBuilder()
        builder.append(text)   // Append the text to the builder

        // Set the color for the appended text
        val span = ForegroundColorSpan(color)
        builder.setSpan(span,builder.length - text.length,builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.textView3.append(builder)
    }

    private fun getItem(): Int {
        return mViewPager.currentItem
    }
}