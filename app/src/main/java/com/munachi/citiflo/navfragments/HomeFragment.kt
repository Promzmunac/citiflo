package com.munachi.citiflo.navfragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onlinemarket.onboard_and_Auth.model.LikeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.munachi.citiflo.BikerActivity
import com.munachi.citiflo.R
import com.munachi.citiflo.adapter.LikedOnClickInterface
import com.munachi.citiflo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
   // private var likeDBRef = Firebase.firestore.collection("LikedProducts")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseReference = FirebaseDatabase.getInstance().getReference("products")
        auth = FirebaseAuth.getInstance()

        dropDownMenu()
       // updateUiInteraction()
        binding.bikeImage.setOnClickListener {
            //findNavController().navigate(R.id.action_homeFragment_to_saveFragment)
            val animation = AnimationUtils.loadAnimation( requireContext() , R.anim.fade_in)
            val intent = Intent(requireContext(),BikerActivity::class.java)
            startActivity(intent)
        }
    }

     private fun dropDownMenu(){
        // get reference to the string array that we just created
        val languages = resources.getStringArray(R.array.Location)
        // create an array adapter and pass the context, drop down layout , and array.
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, languages)
        // get reference to the autocomplete text view
        val autocompleteTV = binding.autoCompleteTextView
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter)
    }

}