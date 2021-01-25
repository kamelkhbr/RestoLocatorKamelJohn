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
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import org.mousehole.restolocatorkameljohn.R
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.LOCATION_REQUEST_CODE
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.TAG
import org.mousehole.restolocatorkameljohn.viewmodel.PlacesViewModel
import kotlin.math.log

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {

    private lateinit var locationManager: LocationManager
    private lateinit var mMap: GoogleMap

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private  var currentLat: Double = 0.0
    private  var currentLong: Double = 0.0
    private  var cameraLat = 0.0
    private  var cameraLong = 0.0

    private lateinit var currentLocationResetButton: CardView

    private lateinit var placeViewModel: PlacesViewModel

    private lateinit var searchButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        searchButton = findViewById(R.id.btn_search_area)

        searchButton.setOnClickListener {
            // Searches nearby places based on camera's location then Intents to new activity
            Toast.makeText(this, "Position: ${mMap.cameraPosition}", Toast.LENGTH_SHORT ).show()
        }

        placeViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(this.application))
            .get(PlacesViewModel::class.java)


        placeViewModel.locationLiveData.observe(this, Observer {
            if(it != null){
                Log.d(TAG, "onCreate: ${it}")
            } else {
                Log.d(TAG, "onCreate: Failed.........")
            }
        })
        //placeViewModel.getPlaceResultSearchRetro("33.9091,-84.4791", "1500")

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        
        currentLocationResetButton = findViewById(R.id.btn_reset_current)
        currentLocationResetButton.setOnClickListener {
           moveCameraLocation(LatLng(currentLat, currentLong))
        }
    }


    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()

        requestLocationPermission()
        registerLocationManager()



    }

    override fun onResume() {
        super.onResume()
        getCurrentLocation()

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
    }

    override fun onLocationChanged(location: Location) {

        // Gets the current long and lat of the previous location
        getCurrentLocation()

        Log.d(TAG, "onLocationChanged: getCurrentLocation $currentLat and $currentLong")

        // Not where I want to put this. Find better spot
        moveCameraLocation(LatLng(currentLat, currentLong))


        //moveCameraLocation(LatLng())
    }

    private fun moveCameraLocation(latLng: LatLng) {
        if (this::mMap.isInitialized) {

            Log.d(TAG, "onLocationChanged: Current Location -> ${latLng}")
            /*mMap.addMarker(MarkerOptions().position(currentLocation).title("My Current Location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))*/
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

        } else{
            Log.d(TAG, "moveCameraLocation: Not Initialized yet")
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

        // Getting current lat and current long
        task.addOnSuccessListener{
            if(it != null){
                currentLat = it.latitude
                currentLong = it.longitude
            }
        }
    }
}