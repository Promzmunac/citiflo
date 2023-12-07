package com.munachi.citiflo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.munachi.citiflo.R
import com.munachi.citiflo.databinding.BikeItemLayoutBinding
import com.munachi.citiflo.model.Biker

class BikersAdapter(private val context: Context, var item: ArrayList<Biker>)
    : RecyclerView.Adapter<BikersAdapter.IceCreamViewHolder>() {

    private lateinit var mListener: onItemClickedListener

    interface onItemClickedListener {
        fun onItemClicked(position: Int) {
        }
    }

    fun setOnClickListener(listener: onItemClickedListener) {
        mListener = listener
    }

    inner class IceCreamViewHolder(
        val binding: BikeItemLayoutBinding,
        listener: onItemClickedListener
    ) : RecyclerView.ViewHolder(binding.root) {

        val bikePhotoRv = binding.PhotoUriRv
        val deliveryTypeRv = binding.deliveryTypeRv
        val deliveryProductsRv = binding.deliveryProductsRv
        val bikerRouteRv = binding.bikerRouteRv
        val bikeNameRv = binding.bikeNameRv
        val bikeRatingRv = binding.bikeRatingRv

        init {
            itemView.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IceCreamViewHolder {
        val layout = BikeItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return IceCreamViewHolder(layout, mListener)
    }

    override fun getItemCount(): Int {

        return item.size
    }

    override fun onBindViewHolder(holder: IceCreamViewHolder, position: Int) {
        val current = item[position]

        holder.deliveryTypeRv.text = current.deliveryType
        holder.deliveryProductsRv.text= current.deliveryProducts
        holder.bikerRouteRv.text = current.bikerRoute
        holder.bikeNameRv.text = current.bikeName.toString()
        holder.bikeRatingRv.setText(current.bikeRating)

        Glide.with(context)
            .load(current.PhotoUri)
            .placeholder(R.drawable.alert_circle)
            .into(holder.bikePhotoRv)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun onApplySearch(newList: List<Biker>) {

        this.item = newList as ArrayList<Biker>
        notifyDataSetChanged()
    }
}



