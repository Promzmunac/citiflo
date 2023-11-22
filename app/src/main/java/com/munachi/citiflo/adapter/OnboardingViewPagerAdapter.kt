package com.munachi.citiflo.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.munachi.citiflo.R
import com.munachi.citiflo.onboardfragments.FirstonboardFragment
import com.munachi.citiflo.onboardfragments.SecondonBoardFragment


class OnboardingViewPagerAdapter(fragmentActivity: FragmentActivity, private val context: Context)
    : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstonboardFragment.newInstance(
                context.resources.getString(R.string.title_onboarding_1),
                context.resources.getString(R.string.connects_clients),
                R.drawable.oneboardone
            )

            else -> SecondonBoardFragment.newInstance(
                context.resources.getString(R.string.Hire_or_enlist),
                context.resources.getString(R.string.Enhances_your_business),
                R.drawable.onboardvans
            )
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}