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
import com.munachi.citiflo.adapter.BusAdapter
import com.munachi.citiflo.databinding.ActivityBusBinding
import com.munachi.citiflo.helperclasses.BusHelperClass
import com.munachi.citiflo.model.BusOwner
import java.util.Locale


@Suppress("SENSELESS_COMPARISON")
class BusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusBinding
    private lateinit var gridRecycler: RecyclerView
    private lateinit var adapter: BusAdapter

    private lateinit var searchView: SearchView

    private var newArrayList: ArrayList<BusOwner> = arrayListOf()
    private var tempArrayList: ArrayList<BusOwner> = arrayListOf()
    lateinit var photoId: Array<Int>
    lateinit var busDeliveryType: Array<String>
    lateinit var busDeliveryProduct: Array<String>
    lateinit var busRoute: Array<String>
    lateinit var busName: Array<String>
    lateinit var busRating: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.md_green_900)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.md_green_900)

        photoId = BusHelperClass.busHeadingPhotoUri
        busDeliveryType = BusHelperClass.busHeadingDeliveryType
        busDeliveryProduct = BusHelperClass.busHeadingDeliveryProducts
        busRoute = BusHelperClass.busHeadingRoute
        busName = BusHelperClass.busHeadingName
        busRating = BusHelperClass.busHeadingRating

        searchView = binding.searchView
        gridRecycler = binding.busRv
        gridRecycler.setHasFixedSize(true)
        gridRecycler.layoutManager = GridLayoutManager(this, 2)

        getUserData()
        navFunctionalities()
    }

    private fun getUserData() {

        lateinit var item: BusOwner

        for (i in photoId.indices) {
            item = BusOwner(
                busPhotoUri =  photoId[i],
                busdeliveryType = busDeliveryType[i],
                busdeliveryProducts = busDeliveryProduct[i],
                busRoute = busRoute[i],
                busName = busName[i],
                busRating = busRating[i]
            )
            newArrayList.add(item)
        }

        tempArrayList.addAll(newArrayList)

        adapter = BusAdapter(this, tempArrayList)

        binding.busRv.adapter = adapter

        adapter.setOnClickListener(object : BusAdapter.onItemClickedListener {

            override fun onItemClicked(position: Int) {
                val current = newArrayList[position]
                val intent = Intent(this@BusActivity, DetailsActivity::class.java)
                intent.putExtra("photoId", current.busPhotoUri)
                intent.putExtra("deliveryType", current.busdeliveryType)
                intent.putExtra("deliveryProduct", current.busdeliveryProducts)
                intent.putExtra("bikerRoute",  current.busRoute)
                intent.putExtra("bikeName", current.busName)
                intent.putExtra("bikeRating", current.busRating)

                startActivity(intent)
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                val searchList = ArrayList<BusOwner>()

                if (newText != null){
                    for (i in tempArrayList){
                       if (i.busdeliveryProducts!!.lowercase(Locale.ROOT).contains(newText) ||
                           i.busdeliveryType!!.lowercase(Locale.ROOT).contains(newText) ||
                           i.busName!!.lowercase(Locale.ROOT).contains(newText)||
                           i.busRoute!!.lowercase(Locale.ROOT).contains(newText)){
                           searchList.add(i)
                       }
                      
                    }
                    if (searchList.isEmpty()){
                        Toast.makeText(this@BusActivity, "No Data", Toast.LENGTH_LONG).show()
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



