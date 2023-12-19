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
import com.munachi.citiflo.adapter.BusAdapter
import com.munachi.citiflo.adapter.VanAdapter
import com.munachi.citiflo.databinding.ActivityBusBinding
import com.munachi.citiflo.databinding.ActivityVanBinding
import com.munachi.citiflo.helperclasses.BusHelperClass
import com.munachi.citiflo.helperclasses.vanHelperClass
import com.munachi.citiflo.model.BusOwner
import com.munachi.citiflo.model.VanOwner
import java.util.Locale

class VanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVanBinding
    private lateinit var gridRecycler: RecyclerView
    private lateinit var adapter: VanAdapter

    private lateinit var searchView: SearchView

    private var newArrayList: ArrayList<VanOwner> = arrayListOf()
    private var tempArrayList: ArrayList<VanOwner> = arrayListOf()
    lateinit var photoId: Array<Int>
    lateinit var vanDeliveryType: Array<String>
    lateinit var vanDeliveryProduct: Array<String>
    lateinit var vanRoute: Array<String>
    lateinit var vanName: Array<String>
    lateinit var vanRating: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.md_green_900)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.md_green_900)

        photoId = vanHelperClass.vanHeadingPhotoUri
        vanDeliveryType = vanHelperClass.vanHeadingDeliveryType
        vanDeliveryProduct = vanHelperClass.vanHeadingDeliveryProducts
        vanRoute = vanHelperClass.vanHeadingRoute
        vanName = vanHelperClass.vanHeadingName
        vanRating = vanHelperClass.vanHeadingRating

        searchView = binding.searchView
        gridRecycler = binding.vanRv
        gridRecycler.setHasFixedSize(true)
        gridRecycler.layoutManager = GridLayoutManager(this, 2)

        getUserData()
        navFunctionalities()
    }

    private fun getUserData() {

        lateinit var item: VanOwner

        for (i in photoId.indices) {
            item = VanOwner(
                vanPhotoUri =  photoId[i],
                vanDeliveryType = vanDeliveryType[i],
                vanDeliveryProducts = vanDeliveryProduct[i],
                vanRoute = vanRoute[i],
                vanName = vanName[i],
                vanRating = vanRating[i]
            )
            newArrayList.add(item)
        }

        tempArrayList.addAll(newArrayList)

        adapter = VanAdapter(this, tempArrayList)

        binding.vanRv.adapter = adapter

        adapter.setOnClickListener(object : VanAdapter.onItemClickedListener {

            override fun onItemClicked(position: Int) {
                val current = newArrayList[position]
                val intent = Intent(this@VanActivity, DetailsActivity::class.java)
                intent.putExtra("photoId", current.vanPhotoUri)
                intent.putExtra("deliveryType", current.vanDeliveryType)
                intent.putExtra("deliveryProduct", current.vanDeliveryProducts)
                intent.putExtra("bikerRoute",  current.vanRoute)
                intent.putExtra("bikeName", current.vanName)
                intent.putExtra("bikeRating", current.vanRating)

                startActivity(intent)
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                val searchList = ArrayList<VanOwner>()

                if (newText != null){
                    for (i in tempArrayList){
                        if (i.vanDeliveryProducts!!.lowercase(Locale.ROOT).contains(newText) ||
                            i.vanDeliveryType!!.lowercase(Locale.ROOT).contains(newText) ||
                            i.vanName!!.lowercase(Locale.ROOT).contains(newText)||
                            i.vanRoute!!.lowercase(Locale.ROOT).contains(newText)){
                            searchList.add(i)
                        }

                    }
                    if (searchList.isEmpty()){
                        Toast.makeText(this@VanActivity, "No Data", Toast.LENGTH_LONG).show()
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