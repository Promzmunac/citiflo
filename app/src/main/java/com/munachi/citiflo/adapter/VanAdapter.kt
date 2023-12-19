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
import com.munachi.citiflo.databinding.VanItemLayoutBinding
import com.munachi.citiflo.model.Biker
import com.munachi.citiflo.model.BusOwner
import com.munachi.citiflo.model.VanOwner

class VanAdapter(private val context: Context, var item: ArrayList<VanOwner>)
    : RecyclerView.Adapter<VanAdapter.IceCreamViewHolder>() {

    private lateinit var mListener: onItemClickedListener

    interface onItemClickedListener {
        fun onItemClicked(position: Int) {
        }
    }

    fun setOnClickListener(listener: onItemClickedListener) {
        mListener = listener
    }

    inner class IceCreamViewHolder(
        val binding: VanItemLayoutBinding,
        listener: onItemClickedListener
    ) : RecyclerView.ViewHolder(binding.root) {

        val vanPhotoRv = binding.PhotoUriRv
        val vanDeliveryTypeRv = binding.vanDeliveryTypeRv
        val vanDeliveryProductsRv = binding.vanDeliveryProductsRv
        val vanRouteRv = binding.vanRouteRv
        val vanNameRv = binding.vanNameRv
        val vanRatingRv = binding.vanRatingRv

        init {
            itemView.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IceCreamViewHolder {
        val layout = VanItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return IceCreamViewHolder(layout, mListener)
    }

    override fun getItemCount(): Int {

        return item.size
    }

    override fun onBindViewHolder(holder: IceCreamViewHolder, position: Int) {
        val current = item[position]



        holder.vanDeliveryTypeRv.text = current.vanDeliveryType
        holder.vanDeliveryProductsRv.text= current.vanDeliveryProducts
        holder.vanRouteRv.text = current.vanRoute!!.trim()
        holder.vanNameRv.text = current.vanName.toString().trim()
        holder.vanRatingRv.setText(current.vanRating)

        Glide.with(context)
            .load(current.vanPhotoUri)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            //.error(placeholder)
            .placeholder(R.drawable.alert_circle)
          /*  .dontTransform()
            .override(200,200)*/
            .into(holder.vanPhotoRv)

    }

    @Suppress("UNCHECKED_CAST")
    @SuppressLint("NotifyDataSetChanged")
    fun onApplySearch(newList: List<VanOwner>) {

        this.item = newList as ArrayList<VanOwner>
        notifyDataSetChanged()
    }
}



