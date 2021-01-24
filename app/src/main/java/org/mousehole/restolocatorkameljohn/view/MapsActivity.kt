package org.mousehole.restolocatorkameljohn.view

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.mousehole.restolocatorkameljohn.R

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {

    private lateinit var locationManager: LocationManager
    private lateinit var mMap: GoogleMap



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }




    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000L,2f,this)

    }

    override fun onStop() {
        super.onStop()
        locationManager.removeUpdates(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun onLocationChanged(location: Location) {

        if (this::mMap.isInitialized) {
            val currentLocation = LatLng(location.latitude,location.longitude)
            mMap.addMarker(MarkerOptions().position(currentLocation).title("My Current Location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))

        }
    }
}