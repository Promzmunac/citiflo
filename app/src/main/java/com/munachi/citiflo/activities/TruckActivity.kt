package com.munachi.citiflo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.munachi.citiflo.R
import com.munachi.citiflo.adapter.TruckAdapter
import com.munachi.citiflo.adapter.VanAdapter
import com.munachi.citiflo.databinding.ActivityTruckBinding
import com.munachi.citiflo.databinding.ActivityVanBinding
import com.munachi.citiflo.helperclasses.TruckHelperClass
import com.munachi.citiflo.helperclasses.vanHelperClass
import com.munachi.citiflo.model.TruckOwner
import com.munachi.citiflo.model.VanOwner
import java.util.Locale

class TruckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTruckBinding
    private lateinit var gridRecycler: RecyclerView
    private lateinit var adapter: TruckAdapter

    private lateinit var searchView: SearchView

    private var newArrayList: ArrayList<TruckOwner> = arrayListOf()
    private var tempArrayList: ArrayList<TruckOwner> = arrayListOf()
    lateinit var photoId: Array<Int>
    lateinit var truckDeliveryType: Array<String>
    lateinit var truckDeliveryProduct: Array<String>
    lateinit var truckRoute: Array<String>
    lateinit var truckName: Array<String>
    lateinit var truckRating: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTruckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.md_green_900)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.md_green_900)

        photoId = TruckHelperClass.truckHeadingPhotoUri
        truckDeliveryType = TruckHelperClass.truckHeadingDeliveryType
        truckDeliveryProduct = TruckHelperClass.truckHeadingDeliveryProducts
        truckRoute = TruckHelperClass.truckHeadingRoute
        truckName = TruckHelperClass.truckHeadingName
        truckRating = TruckHelperClass.truckHeadingRating

        searchView = binding.searchView
        gridRecycler = binding.truckRv
        gridRecycler.setHasFixedSize(true)
        gridRecycler.layoutManager = GridLayoutManager(this, 2)

        getUserData()
        navFunctionalities()
    }

    private fun getUserData() {

        lateinit var item: TruckOwner

        for (i in photoId.indices) {
            item = TruckOwner(
                truckPhotoUri =  photoId[i],
                truckDeliveryType = truckDeliveryType[i],
                truckDeliveryProducts = truckDeliveryProduct[i],
                truckRoute = truckRoute[i],
                truckName = truckName[i],
                truckRating = truckRating[i]
            )
            newArrayList.add(item)
        }

        tempArrayList.addAll(newArrayList)

        adapter = TruckAdapter(this, tempArrayList)

        binding.truckRv.adapter = adapter

        adapter.setOnClickListener(object : TruckAdapter.onItemClickedListener {

            override fun onItemClicked(position: Int) {
                val current = newArrayList[position]
                val intent = Intent(this@TruckActivity, DetailsActivity::class.java)
                intent.putExtra("photoId", current.truckPhotoUri)
                intent.putExtra("deliveryType", current.truckDeliveryType)
                intent.putExtra("deliveryProduct", current.truckDeliveryProducts)
                intent.putExtra("bikerRoute",  current.truckRoute)
                intent.putExtra("bikeName", current.truckName)
                intent.putExtra("bikeRating", current.truckRating)

                startActivity(intent)
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                val searchList = ArrayList<TruckOwner>()

                if (newText != null){
                    for (i in tempArrayList){
                        if (i.truckDeliveryProducts!!.lowercase(Locale.ROOT).contains(newText) ||
                            i.truckDeliveryType!!.lowercase(Locale.ROOT).contains(newText) ||
                            i.truckName!!.lowercase(Locale.ROOT).contains(newText)||
                            i.truckRoute!!.lowercase(Locale.ROOT).contains(newText)){
                            searchList.add(i)
                        }

                    }
                    if (searchList.isEmpty()){
                        Toast.makeText(this@TruckActivity, "No Data", Toast.LENGTH_LONG).show()
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