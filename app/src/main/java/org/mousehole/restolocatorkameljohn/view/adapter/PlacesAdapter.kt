package org.mousehole.restolocatorkameljohn.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.mousehole.restolocatorkameljohn.R
import org.mousehole.restolocatorkameljohn.model.PlacesResult
import org.mousehole.restolocatorkameljohn.view.adapter.PlacesAdapter.*

class PlacesAdapter(private var placesList: MutableList<PlacesResult>): RecyclerView.Adapter<PlacesViewHolder>() {

    fun updatePlacesList (placesList: MutableList<PlacesResult>){
        this.placesList = placesList
        notifyDataSetChanged()
    }


    class PlacesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val businessName : TextView = itemView.findViewById(R.id.business_name)
        val businessStatus : TextView = itemView.findViewById(R.id.business_status)
        val openingHours : TextView = itemView.findViewById(R.id.open_now)
        val rating : TextView = itemView.findViewById(R.id.rating)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.places_result_item,parent, false)
        return PlacesViewHolder(itemView)
    }


    override fun getItemCount(): Int = placesList.size

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        val placesItem = placesList[position]

        holder.apply {
            businessName.text = placesItem.name
            businessStatus.text = placesItem.business_status
            openingHours.text = placesItem.opening_hours.toString()
            rating.text = placesItem.rating.toString()

        }

    }


}