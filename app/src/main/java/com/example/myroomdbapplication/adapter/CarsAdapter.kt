package com.example.myroomdbapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomdbapplication.R
import com.example.myroomdbapplication.database.CarEntity

class CarsAdapter(private val availableCars: List<CarEntity>, private val carAdapterDelegate: CarAdapterDelegate) :
    RecyclerView.Adapter<CarsAdapter.CarViewHolder>() {
    interface CarAdapterDelegate{
        fun deleteCar(car: CarEntity)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.car_item_layout,
                parent,
                false
            )
        return CarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return availableCars.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.apply {
            carName.text = "${availableCars[position].carName} ${availableCars[position].carModel}"
            carPrice.text = availableCars[position].carPrice.toDollar()
        }
        holder.itemView.setOnClickListener {
            carAdapterDelegate.deleteCar(availableCars[position])
        }
    }

    private fun Int.toDollar(): String {
        return "$this.00"
    }

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carName: TextView = itemView.findViewById(R.id.car_name_textview)
        val carPrice: TextView = itemView.findViewById(R.id.car_price_textview)
    }
}