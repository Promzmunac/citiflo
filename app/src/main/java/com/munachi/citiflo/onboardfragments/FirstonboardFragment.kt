package com.munachi.citiflo.onboardfragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.munachi.citiflo.databinding.FragmentFirstonboardBinding

class FirstonboardFragment : Fragment() {
    private lateinit var binding: FragmentFirstonboardBinding
    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvDescription: AppCompatTextView
    private lateinit var image: ImageView

    private var title: String? = null
    private var description: String? = null
    private var imageResource = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFirstonboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            title = requireArguments().getString(FIRST)
            description = requireArguments().getString(SECOND)
            imageResource = requireArguments().getInt(THIRD)
        }

        tvTitle = binding.textOnboardingTitle
        tvDescription = binding.textOnboardingDescription
        image = binding.imageOnboardingOne
        tvTitle.text = title
        tvDescription.text = description
        image.setImageURI(Uri.parse(imageResource.toString()))

    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        const val FIRST = "first"
        const val SECOND = "second"
        const val THIRD = "third"

        fun newInstance(
            title: String?,
            description: String?,
            imageResource: Int
        ): FirstonboardFragment {
            val fragment = FirstonboardFragment()
            val args = Bundle()
            args.putString(FIRST, title)
            args.putString(SECOND, description)
            args.putInt(THIRD, imageResource)

            fragment.arguments = args

            return fragment
        }
    }
}