package com.gal.investor.display.apartments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gal.investor.R
import com.gal.investor.data.entities.Apartment

class ApartmentsAdapter(
    private val apartmentItemListener: ApartmentItemListener, var apartments:
    List<Apartment?>? = null
) :
    RecyclerView.Adapter<ApartmentsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item, parent,
            false
        )
        return ViewHolder(view, apartmentItemListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(apartments?.get(position))
    }

    override fun getItemCount(): Int {
        return apartments?.size ?: 0
    }

    fun setApartmentsList(apartments: List<Apartment?>?) {
        this.apartments = apartments
        notifyDataSetChanged()
    }

    class ViewHolder(ItemView: View, var apartmentItemListener: ApartmentItemListener) :
        RecyclerView.ViewHolder(ItemView) {
        fun bind(apartment: Apartment?) {
            apartment?.let {
                itemView.findViewById<TextView>(R.id.name_tv).text = "Name: " + apartment.name
                itemView.findViewById<TextView>(R.id.location_tv).text =
                    "Location: " + apartment.location
                itemView.setOnClickListener {
                    apartmentItemListener.onClick(apartment)
                }
            }

        }
    }

    interface ApartmentItemListener {
        fun onClick(apartment: Apartment)
    }
}