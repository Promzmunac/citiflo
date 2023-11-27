package com.munachi.citiflo.navfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.onlinemarket.onboard_and_Auth.model.LikeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.munachi.citiflo.adapter.LikedOnClickInterface
import com.munachi.citiflo.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), LikedOnClickInterface {
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

       // Spinner()
        dropDownMenu()
    }

    private fun dropDownMenu(){
        val spinner: Spinner = binding.spinnerDropDown

        val items: List<String> = listOf("Lagos", "Abuja", "PortHarcourt", "Ghana", "South Africa", "United States")
        val adapter: ArrayAdapter<Any?> =
            ArrayAdapter<Any?>(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

   /* fun Spinner(){

        val nigerianStates = resources.getStringArray(R.array.Nigeria)
        val spinner = binding.spinnerDropDown
        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item, nigerianStates)
        spinner.adapter = adapter


        spinner.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>,view: View, position: Int, id: Long) {

                val msg = getString(R.string.Nigeria_Displayed) + " " + "" + nigerianStates[position]

                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }*/

    override fun onClickLike(item: LikeModel) {
        TODO("Not yet implemented")
    }

}