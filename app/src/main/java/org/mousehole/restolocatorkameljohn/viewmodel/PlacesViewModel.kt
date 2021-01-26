package org.mousehole.restolocatorkameljohn.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mousehole.restolocatorkameljohn.model.data.LocationPlace
import androidx.lifecycle.Observer
import org.mousehole.restolocatorkameljohn.model.data.PlacesResult
import org.mousehole.restolocatorkameljohn.model.db.LocationRepository

class PlacesViewModel(application: Application) :ViewModel(){

    private val repository : LocationRepository = LocationRepository(application)

    val locationLiveData: MutableLiveData<List<PlacesResult>> = repository.placesLiveData

    lateinit var observer: Observer<List<PlacesResult>>

    fun getPlaceResultSearchRetro(location: String, radius: String){
        repository.getPlaceResults(location, radius)
        storeLastLocationResult()
    }

    fun insertPlaceDB(location: LocationPlace){
        repository.insertLocationDB(location)
    }

    fun deletePlaceDB(location: LocationPlace){
        repository.deleteLocationDB(location)
    }

    fun updatePlaceDB(location: LocationPlace){
        repository.updateLocationDB(location)
    }

    fun clearDB(){
        repository.clearLocationDB()
    }

    fun getPlaceResultSearchDB() = repository.getAllLocationDB()

    fun storeLastLocationResult(){
        clearDB()
        observer = Observer {
            it.forEach {

                val name:String? = it.name
                val businessStatus:String? = it.business_status
                val imageIconUrl:String? = it.icon
                val lat:String? = it.geometry.location.lat.toString()
                val long:String? = it.geometry.location.lng.toString()
                var openingHours: String? = null
                if(it.opening_hours == null){
                    Log.d(TAG, "storeLastLocationResult: is Null")
                    openingHours = ""
                } else {
                    Log.d(TAG, "storeLastLocationResult: is not null")
                    openingHours = it.opening_hours.open_now.toString()
                }
                val rating:String? = it.rating.toString()
                insertPlaceDB(
                    LocationPlace(
                            name,
                            businessStatus,
                            imageIconUrl,
                            lat,
                            long,
                            openingHours,
                            rating

                    )
                )
            }
        }
        locationLiveData.observeForever(observer)
    }

    override fun onCleared() {
        super.onCleared()
        locationLiveData.removeObserver(observer)
    }

}
