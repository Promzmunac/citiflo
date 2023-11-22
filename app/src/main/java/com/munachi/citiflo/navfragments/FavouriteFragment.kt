package com.munachi.citiflo.navfragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinemarket.onboard_and_Auth.model.LikeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.munachi.citiflo.adapter.FavouriteAdapter
import com.munachi.citiflo.adapter.LikedOnClickInterface
import com.munachi.citiflo.adapter.LikedProductOnClickInterface
import com.munachi.citiflo.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() , LikedProductOnClickInterface, LikedOnClickInterface {

    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var adapter: FavouriteAdapter
    private lateinit var favorList: ArrayList<LikeModel>
    private var likeDBRef = Firebase.firestore.collection("LikedProducts")
    private lateinit var toolbar: Toolbar
    private lateinit var userRecyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
       binding = FragmentFavouriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()
        toolbar = binding.likeToolbar

        //set up adapter class
        favorList = ArrayList()
        adapter = FavouriteAdapter(requireContext(),favorList,this, this)

        //set up recycler view
        userRecyclerView = binding.favoriteRv
        userRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        userRecyclerView.adapter = adapter

        binding.likeActualToolbar.setOnClickListener {
            (activity as AppCompatActivity).onBackPressed()
        }

       displayLikedProducts()

    }

    private fun displayLikedProducts() {

        likeDBRef
            .whereEqualTo("uid" , firebaseAuth.currentUser!!.uid)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (item in querySnapshot) {
                    val likedProduct = item.toObject<LikeModel>()

                    favorList.add(likedProduct)
                    adapter.notifyDataSetChanged()
                }
            }

            .addOnFailureListener{
                    //toast
            }
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onClickLike(item: LikeModel) {

        likeDBRef
            .whereEqualTo("uid",firebaseAuth.currentUser!!.uid)
            .whereEqualTo("pid",item.pid)
            .get()
            .addOnSuccessListener { querySnapshot ->

                for (item in querySnapshot){
                    likeDBRef.document(item.id).delete()

                    favorList.remove(item.toObject())
                    adapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "Removed",Toast.LENGTH_SHORT).show()

                }
            }

            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed",Toast.LENGTH_SHORT).show()
            }
    }

    override fun onClickProduct(item: LikeModel) {
        TODO("Not yet implemented")
    }
}


