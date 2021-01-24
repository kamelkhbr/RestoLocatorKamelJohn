package org.mousehole.restolocatorkameljohn.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import org.mousehole.restolocatorkameljohn.R
import org.mousehole.restolocatorkameljohn.model.PlacesResult
import org.mousehole.restolocatorkameljohn.view.adapter.PlacesAdapter
import org.mousehole.restolocatorkameljohn.viewmodel.PlacesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    private val viewModel: PlacesViewModel by viewModels()
    private lateinit var placesRecyclerView: RecyclerView
    private val placesAdapter: PlacesAdapter = PlacesAdapter(mutableListOf())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Creating the menu file for the main acitvity
        toolbar = findViewById(R.id.main_tool_bar)
        setSupportActionBar(toolbar)

        val bundle : Bundle? = intent.extras
        val radius = bundle!!.getString("radius")


        placesRecyclerView = findViewById(R.id.places_recycler)
        placesRecyclerView.adapter = placesAdapter

        viewModel.placesLiveData.observe(this, Observer {
            Log.d("TAG_X", "Places Obtained...${it.size}")
            placesAdapter.updatePlacesList(it as MutableList<PlacesResult>)
        })
        viewModel.getNearBy()
    }



    // For toolbar in main Activity
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

}