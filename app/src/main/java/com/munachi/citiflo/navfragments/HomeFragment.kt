package com.munachi.citiflo.navfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinemarket.onboard_and_Auth.model.LikeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.munachi.citiflo.adapter.LikedOnClickInterface
import com.munachi.citiflo.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), LikedOnClickInterface {
    private lateinit var binding: FragmentHomeBinding
    //private lateinit var gridRecycler: RecyclerView
    //private lateinit var adapter: RecommendedAdapter
   // private var newArrayList: ArrayList<IceCream> = arrayListOf()
   // private var tempArrayList: ArrayList<IceCream> = arrayListOf()
   // private lateinit var productList: ArrayList<IceCream>
    private lateinit var categoryList: ArrayList<String>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var price: Array<String>
    lateinit var description: Array<String>
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private var likeDBRef = Firebase.firestore.collection("LikedProducts")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseReference = FirebaseDatabase.getInstance().getReference("products")
        auth = FirebaseAuth.getInstance()

        //initialise and set recyclerview to fixed size
        //binding.recyclerView2.setHasFixedSize(true)

       /* binding.rvMainCategories.setHasFixedSize(true)
        val categoryLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.rvMainCategories.layoutManager = categoryLayoutManager
       */



        // categoryAdapter = MainCategoryAdapter(categoryList, this)
        //binding.rvMainCategories.adapter = categoryAdapter
        //setCategoryList()

      /*  heading = HelperClass.headingName
        imageId = HelperClass.headingImageId
        price = HelperClass.headingPrice
        description = HelperClass.headingDescription
*/
        //gridRecycler = binding.recyclerView2
       /* gridRecycler.setHasFixedSize(true)
        gridRecycler.layoutManager = GridLayoutManager(requireContext(), 3)
*/
       // getUserData()


    }

    override fun onClickLike(item: LikeModel) {
        TODO("Not yet implemented")
    }

    /*  private fun getUserData() {

          lateinit var item: IceCream

          // Iterate through the 'imageId' array and
          // create 'IceCream' objects based on the data
          for (i in imageId.indices) {
              item = IceCream(
                  image = imageId[i],
                  name = heading[i],
                  price = price[i],
                  description = description[i]
              )
              // Add the 'item' to 'newArrayList'
              newArrayList.add(item)
          }

          // Copy all elements from 'newArrayList' to 'tempArrayList
          tempArrayList.addAll(newArrayList)

          // Create an instance of 'RecommendedAdapter' with the data from 'tempArrayList'
          adapter = RecommendedAdapter(requireContext(),  tempArrayList, this)

          // Set the 'adapter' to the RecyclerView
          binding.recyclerView2.adapter = adapter

          // Set an item click listener for the RecyclerView items
          adapter.setOnClickListener(object : RecommendedAdapter.onItemClickedListener {

              override fun onItemClicked(position: Int) {
                  val current = newArrayList[position]
                  val intent = Intent(requireContext(), DetailScreenActivity::class.java)
                  intent.putExtra("heading", current.name)
                  intent.putExtra("imageId", current.image)
                  intent.putExtra("description", current.description)
                  intent.putExtra("price", "₦${current.price}")
                  startActivity(intent)
              }
          })
      }*/

 /*   override fun onClickLike(item: IceCream) {

        likeDBRef
            .add(
                LikeModel(item.id, auth.currentUser!!.uid,
                item.description,
                item.image,
                item.name,
                item.price)
            )

            .addOnSuccessListener {
                Toast.makeText(requireContext(),"Added to Favourite Item", Toast.LENGTH_SHORT).show()
            }

            .addOnFailureListener {
                Toast.makeText(requireContext(),"Failed to add to Favourite Item", Toast.LENGTH_SHORT).show()
            }
    }

    private fun setCategoryList() {

        val valueEvent = object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {

                categoryList.clear()
                categoryList.add("Trending")

                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val products = dataSnapshot.getValue(IceCream::class.java)

                        categoryList.add(products!!.brand!!)

                    }
                    categoryAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
            }
        }
        databaseReference.addValueEventListener(valueEvent)
    }

    override fun onClickCategory(button: Button) {
        //binding.rvMainCategories.text = button.text

        val valueEvent = object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()

                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val products = dataSnapshot.getValue(IceCream::class.java)

                        if (products!!.brand == button.text) {
                            productList.add(products)
                        }

                        if (button.text == "Trending") {
                            productList.add(products)
                        }
                    }

                    productsAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
            }
        }
        databaseReference.addValueEventListener(valueEvent)
    }*/
}