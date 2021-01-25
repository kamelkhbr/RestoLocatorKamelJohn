package org.mousehole.restolocatorkameljohn.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.mousehole.restolocatorkameljohn.R
import org.mousehole.restolocatorkameljohn.util.Constants
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.LOCATION_REQUEST_CODE
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.TAG

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {



    private lateinit var searchEditText: TextInputEditText



    private lateinit var locationManager: LocationManager
    private lateinit var mMap: GoogleMap

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private  var currentLat: Double = 0.0
    private  var currentLong: Double = 0.0

    private lateinit var currentLocaitonResetButton: CardView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        searchEditText = findViewById(R.id.search_cities)

        searchEditText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->

            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP ){
                // Get the location (city) text
                searchEditText.text.toString()
                // Find that location on the map
                // Move the camera to that location
                Log.d("TAG_K",searchEditText.text.toString() )

                return@OnKeyListener true
            }
            false
        })




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        currentLocaitonResetButton = findViewById(R.id.btn_reset_current)
        currentLocaitonResetButton.setOnClickListener {
            moveCameraLocation()
        }
    }



    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()

        requestLocationPermission()
        registerLocationManager()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(LOCATION_REQUEST_CODE == requestCode){
            if(permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION){
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: Location Permission Granted")
                    registerLocationManager()
                } else {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission
                            .ACCESS_FINE_LOCATION)){ // Checks if check box is checked to not ask again
                        requestLocationPermission()
                    } else {
                        // At this point, let the user know that they have to enable permission manually
                        // to use this application
                        Log.d(TAG, "onRequestPermissionsResult: Rationale blocked...")
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        locationManager.removeUpdates(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        Log.d(TAG, "onMapReady: ")
    }

    override fun onLocationChanged(location: Location) {

        // Gets the current long and lat of the previous location
        getCurrentLocation()

        Log.d(TAG, "onLocationChanged: getCurrentLocation $currentLat and $currentLong")


        moveCameraLocation()
    }

    private fun moveCameraLocation() {
        if (this::mMap.isInitialized) {
            val currentLocation = LatLng(currentLat, currentLong)

            Log.d(TAG, "onLocationChanged: Current Location -> ${currentLocation}")
            /*mMap.addMarker(MarkerOptions().position(currentLocation).title("My Current Location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))*/
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))

        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
    }

    @SuppressLint("MissingPermission")
    private fun registerLocationManager() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10.0f, this)
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation(){
       val task: Task<Location> = fusedLocationProviderClient.lastLocation

        //Getting current lat and current long
        task.addOnSuccessListener{
            if(it != null){
                currentLat = it.latitude
                currentLong = it.longitude
            }
        }
    }
}