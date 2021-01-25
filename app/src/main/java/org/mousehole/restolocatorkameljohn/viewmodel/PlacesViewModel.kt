package org.mousehole.restolocatorkameljohn.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import org.mousehole.restolocatorkameljohn.model.data.LocationPlace
import org.mousehole.restolocatorkameljohn.model.data.PlacesResult
import org.mousehole.restolocatorkameljohn.model.db.LocationRepository

class PlacesViewModel(application: Application) : AndroidViewModel(application){

    private val repository : LocationRepository = LocationRepository(application)

    val locationLiveData: MutableLiveData<List<PlacesResult>> = repository.placesLiveData

    fun getPlaceResultSearchRetro(location: String, radius: String){
        repository.getPlaceResults(location, radius)
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

}
