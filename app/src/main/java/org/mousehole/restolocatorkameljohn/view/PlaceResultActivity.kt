package org.mousehole.restolocatorkameljohn.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import org.mousehole.restolocatorkameljohn.R
import org.mousehole.restolocatorkameljohn.model.data.LocationPlace

import org.mousehole.restolocatorkameljohn.util.Constants.Companion.TAG
import org.mousehole.restolocatorkameljohn.view.adapter.PlacesAdapter
import org.mousehole.restolocatorkameljohn.viewmodel.PlacesViewModel

class PlaceResultActivity : AppCompatActivity() {

    private var placeList: List<LocationPlace> = ArrayList()
    private var cameraLat = 0.0
    private var cameraLong = 0.0

    private lateinit var rvPlaceList: RecyclerView
    private val adapter: PlacesAdapter = PlacesAdapter(mutableListOf())

    private lateinit var viewModel: PlacesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_result)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application))
            .get(PlacesViewModel::class.java)


        cameraLat = intent.getDoubleExtra("lat", 0.0)
        cameraLong = intent.getDoubleExtra("long", 0.0)

        Log.d(TAG, "onCreate: PlaceResult Activity -> lat = $cameraLat and long = $cameraLong")

        rvPlaceList = findViewById(R.id.rv_place_list)

        viewModel.getPlaceResultSearchRetro("$cameraLat,$cameraLong", "1500")

        viewModel.getPlaceResultSearchDB()?.observe(this, Observer {
            adapter.updatePlacesList(it)
        })

        rvPlaceList.adapter = adapter

    }
}