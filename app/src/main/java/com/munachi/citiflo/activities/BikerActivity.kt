package com.munachi.citiflo.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.munachi.citiflo.R
import com.munachi.citiflo.adapter.BikersAdapter
import com.munachi.citiflo.databinding.ActivityBikerBinding
import com.munachi.citiflo.helperclasses.BikerHelperClass
import com.munachi.citiflo.model.Biker
import java.util.Locale


@Suppress("SENSELESS_COMPARISON")
class BikerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBikerBinding
    private lateinit var gridRecycler: RecyclerView
    private lateinit var adapter: BikersAdapter

    private lateinit var searchView: SearchView

    private var newArrayList: ArrayList<Biker> = arrayListOf()
    private var tempArrayList: ArrayList<Biker> = arrayListOf()
    lateinit var photoId: Array<Int>
    lateinit var deliveryType: Array<String>
    lateinit var deliveryProduct: Array<String>
    lateinit var bikerRoute: Array<String>
    lateinit var bikeName: Array<String>
    lateinit var bikeRating: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBikerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.md_green_900)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.md_green_900)

        photoId = BikerHelperClass.headingPhotoUri
        deliveryType = BikerHelperClass.headingDeliveryType
        deliveryProduct = BikerHelperClass.headingDeliveryProducts
        bikerRoute = BikerHelperClass.headingBikerRoute
        bikeName = BikerHelperClass.headingBikeName
        bikeRating = BikerHelperClass.headingBikeRating

        searchView = binding.searchView
        gridRecycler = binding.bikeRv
        gridRecycler.setHasFixedSize(true)
        gridRecycler.layoutManager = GridLayoutManager(this, 2)

        getUserData()
        navFunctionalities()
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

        adapter = BikersAdapter(this, tempArrayList)

        binding.bikeRv.adapter = adapter

        adapter.setOnClickListener(object : BikersAdapter.onItemClickedListener {

            override fun onItemClicked(position: Int) {
                val current = newArrayList[position]
                val intent = Intent(this@BikerActivity, DetailsActivity::class.java)
                intent.putExtra("photoId", current.PhotoUri)
                intent.putExtra("deliveryType", current.deliveryType)
                intent.putExtra("deliveryProduct", current.deliveryProducts)
                intent.putExtra("bikerRoute",  current.bikerRoute)
                intent.putExtra("bikeName", current.bikeName)
                intent.putExtra("bikeRating", current.bikeRating)

                startActivity(intent)
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                val searchList = ArrayList<Biker>()

                if (newText != null){
                    for (i in tempArrayList){
                       if (i.deliveryProducts!!.lowercase(Locale.ROOT).contains(newText) ||
                           i.deliveryType!!.lowercase(Locale.ROOT).contains(newText) ||
                           i.bikeName!!.lowercase(Locale.ROOT).contains(newText)||
                           i.bikerRoute!!.lowercase(Locale.ROOT).contains(newText) ||
                           i.deliveryProducts!!.lowercase(Locale.ROOT).contains(newText)){
                           searchList.add(i)
                       }
                      
                    }
                    if (searchList.isEmpty()){
                        Toast.makeText(this@BikerActivity, "No Data", Toast.LENGTH_LONG).show()
                    }else{
                        adapter.onApplySearch(searchList)
                    }
                }

                return true
            }
        })
    }

    private fun navFunctionalities(){
        binding.backPress.setOnClickListener {
            onBackPressed()
        }
        binding.menuDots.setOnClickListener {
            Toast.makeText(this, "Menu Clicked", Toast.LENGTH_LONG).show()
        }
    }
}



