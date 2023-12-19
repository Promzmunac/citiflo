package com.munachi.citiflo.navfragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.munachi.citiflo.activities.BikerActivity
import com.munachi.citiflo.activities.BusActivity
import com.munachi.citiflo.activities.DetailsActivity
import com.munachi.citiflo.R
import com.munachi.citiflo.activities.TruckActivity
import com.munachi.citiflo.activities.VanActivity
import com.munachi.citiflo.adapter.HomeFragAdapter
import com.munachi.citiflo.databinding.FragmentHomeBinding
import com.munachi.citiflo.helperclasses.HomeHelperClass
import com.munachi.citiflo.model.Biker

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var searchView: EditText
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var gridRecycler: RecyclerView
    private lateinit var adapter: HomeFragAdapter
    private var newArrayList: ArrayList<Biker> = arrayListOf()
    private var tempArrayList: ArrayList<Biker> = arrayListOf()
    lateinit var photoId: Array<Int>
    lateinit var deliveryType: Array<String>
    lateinit var deliveryProduct: Array<String>
    lateinit var bikerRoute: Array<String>
    lateinit var bikeName: Array<String>
    lateinit var bikeRating: Array<String>

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


        searchView = binding.searchView
        photoId = HomeHelperClass.headingPhotoUri
        deliveryType = HomeHelperClass.headingDeliveryType
        deliveryProduct = HomeHelperClass.headingDeliveryProducts
        bikerRoute = HomeHelperClass.headingBikerRoute
        bikeName = HomeHelperClass.headingBikeName
        bikeRating = HomeHelperClass.headingBikeRating

        gridRecycler = binding.homeRv
        gridRecycler.setHasFixedSize(true)
        gridRecycler.layoutManager = GridLayoutManager(requireContext(), 2)

        dropDownMenu()

        allVehicleHireLayoutsNavigation()

        getUserData()
    }

    private fun allVehicleHireLayoutsNavigation(){

        binding.bikeImage.setOnClickListener {
            val animation = AnimationUtils.loadAnimation( requireContext() , R.anim.fade_in)
            val intent = Intent(requireContext(), BikerActivity::class.java)
            startActivity(intent)
        }

        binding.busImage.setOnClickListener {
            val animation = AnimationUtils.loadAnimation( requireContext() , R.anim.fade_in)
            val intent = Intent(requireContext(), BusActivity::class.java)
            startActivity(intent)
        }

        binding.vanImage.setOnClickListener {
            val animation = AnimationUtils.loadAnimation( requireContext() , R.anim.fade_in)
            val intent = Intent(requireContext(), VanActivity::class.java)
            startActivity(intent)
        }

        binding.truckImage.setOnClickListener {
            val animation = AnimationUtils.loadAnimation( requireContext() , R.anim.fade_in)
            val intent = Intent(requireContext(), TruckActivity::class.java)
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

    private fun getUserData() {

        lateinit var item: Biker

        for (i in photoId.indices) {
            item = Biker(
                PhotoUri = photoId[i],
                deliveryType = deliveryType[i],
                deliveryProducts = deliveryProduct[i],
                bikerRoute = bikerRoute[i],
                bikeName = bikeName[i],
                bikeRating = bikeRating[i]
            )
            newArrayList.add(item)
        }

        tempArrayList.addAll(newArrayList)

        adapter = HomeFragAdapter(requireContext(), tempArrayList)

        binding.homeRv.adapter = adapter

        adapter.setOnClickListener(object : HomeFragAdapter.onItemClickedListener {

            override fun onItemClicked(position: Int) {
                val current = newArrayList[position]
                val intent = Intent(requireContext(), DetailsActivity::class.java)
                intent.putExtra("photoId", current.PhotoUri)
                intent.putExtra("deliveryType", current.deliveryType)
                intent.putExtra("deliveryProduct", current.deliveryProducts)
                intent.putExtra("bikerRoute",  current.bikerRoute)
                intent.putExtra("bikeName", current.bikeName)
                intent.putExtra("bikeRating", current.bikeRating)

                startActivity(intent)
            }
        })
    }
}