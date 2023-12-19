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
import com.munachi.citiflo.databinding.BusItemLayoutBinding
import com.munachi.citiflo.model.Biker
import com.munachi.citiflo.model.BusOwner

class BusAdapter(private val context: Context, var item: ArrayList<BusOwner>)
    : RecyclerView.Adapter<BusAdapter.IceCreamViewHolder>() {

    private lateinit var mListener: onItemClickedListener

    interface onItemClickedListener {
        fun onItemClicked(position: Int) {
        }
    }

    fun setOnClickListener(listener: onItemClickedListener) {
        mListener = listener
    }

    inner class IceCreamViewHolder(
        val binding: BusItemLayoutBinding,
        listener: onItemClickedListener
    ) : RecyclerView.ViewHolder(binding.root) {

        val busPhotoRv = binding.PhotoUriRv
        val busDeliveryTypeRv = binding.busDeliveryTypeRv
        val busDeliveryProductsRv = binding.busDeliveryProductsRv
        val busRouteRv = binding.busRouteRv
        val busNameRv = binding.busNameRv
        val busRatingRv = binding.busRatingRv

        init {
            itemView.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IceCreamViewHolder {
        val layout = BusItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return IceCreamViewHolder(layout, mListener)
    }

    override fun getItemCount(): Int {

        return item.size
    }

    override fun onBindViewHolder(holder: IceCreamViewHolder, position: Int) {
        val current = item[position]

        holder.busDeliveryTypeRv.text = current.busdeliveryType
        holder.busDeliveryProductsRv.text= current.busdeliveryProducts
        holder.busRouteRv.text = current.busRoute!!.trim()
        holder.busNameRv.text = current.busName.toString().trim()
        holder.busRatingRv.setText(current.busRating)

        Glide.with(context)
            .load(current.busPhotoUri)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            //.error(placeholder)
            .placeholder(R.drawable.alert_circle)
          /*  .dontTransform()
            .override(200,200)*/
            .into(holder.busPhotoRv)

    }

    @Suppress("UNCHECKED_CAST")
    @SuppressLint("NotifyDataSetChanged")
    fun onApplySearch(newList: List<BusOwner>) {

        this.item = newList as ArrayList<BusOwner>
        notifyDataSetChanged()
    }
}



