package com.munachi.citiflo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinemarket.onboard_and_Auth.model.LikeModel
import com.munachi.citiflo.databinding.BikeItemLayoutBinding


class FavouriteAdapter (
    private val context: Context,
    private val list: ArrayList<LikeModel>,
    private val productClickInterface: LikedProductOnClickInterface,
    private val likeClickInterface: LikedOnClickInterface ) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: BikeItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutBinding = BikeItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return ViewHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentItem = list[position]

            //holder.binding.favproLike.backgroundTintList = ColorStateList.valueOf(Color.RED)
/*

            Glide
                .with(context)
                .load(currentItem.image)
                .into(holder.binding.proImage)
*/

        holder.itemView.setOnClickListener {
            productClickInterface.onClickProduct(list[position])
        }


       /* holder.binding.favproLike.setOnClickListener {
                likeClickInterface.onClickLike(currentItem)

                holder.binding.favproLike.backgroundTintList = ColorStateList.valueOf(Color.RED)

                likeClickInterface.onClickLike(currentItem)
            }*/
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface LikedOnClickInterface{
    fun onClickLike(item: LikeModel)
}

interface LikedProductOnClickInterface {
    fun onClickProduct(item: LikeModel)
}