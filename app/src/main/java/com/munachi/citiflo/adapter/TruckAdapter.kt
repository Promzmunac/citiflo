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
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.munachi.citiflo.R
import com.munachi.citiflo.databinding.BikeItemLayoutBinding
import com.munachi.citiflo.databinding.TruckItemLayoutBinding
import com.munachi.citiflo.model.Biker
import com.munachi.citiflo.model.TruckOwner

class TruckAdapter(private val context: Context, var item: ArrayList<TruckOwner>)
    : RecyclerView.Adapter<TruckAdapter.IceCreamViewHolder>() {

    private lateinit var mListener: onItemClickedListener

    interface onItemClickedListener {
        fun onItemClicked(position: Int) {
        }
    }

    fun setOnClickListener(listener: onItemClickedListener) {
        mListener = listener
    }

    inner class IceCreamViewHolder(
        val binding: TruckItemLayoutBinding,
        listener: onItemClickedListener
    ) : RecyclerView.ViewHolder(binding.root) {

        val PhotoRv = binding.PhotoUriRv
        val deliveryTypeRv = binding.truckDeliveryTypeRv
        val deliveryProductsRv = binding.truckDeliveryProductsRv
        val truckRouteRv = binding.truckRouteRv
        val truckNameRv = binding.truckNameRv
        val truckRatingRv = binding.truckRatingRv

        init {
            itemView.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IceCreamViewHolder {
        val layout = TruckItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return IceCreamViewHolder(layout, mListener)
    }

    override fun getItemCount(): Int {

        return item.size
    }

    override fun onBindViewHolder(holder: IceCreamViewHolder, position: Int) {
        val current = item[position]

        holder.deliveryTypeRv.text = current.truckDeliveryType
        holder.deliveryProductsRv.text= current.truckDeliveryProducts
        holder.truckRouteRv.text = current.truckRoute!!.trim()
        holder.truckNameRv.text = current.truckName.toString().trim()
        holder.truckRatingRv.setText(current.truckRating)

        Glide.with(context)
            .load(current.truckPhotoUri)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            //.error(placeholder)
            .placeholder(R.drawable.alert_circle)
          /*  .dontTransform()
            .override(200,200)*/
            .into(holder.PhotoRv)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun onApplySearch(newList: List<TruckOwner>) {

        this.item = newList as ArrayList<TruckOwner>
        notifyDataSetChanged()
    }
}



