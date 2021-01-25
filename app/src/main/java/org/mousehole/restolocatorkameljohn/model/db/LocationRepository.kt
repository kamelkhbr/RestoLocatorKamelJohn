package org.mousehole.restolocatorkameljohn.model.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.mousehole.restolocatorkameljohn.model.data.LocationPlace
import org.mousehole.restolocatorkameljohn.model.data.PlacesResult
import org.mousehole.restolocatorkameljohn.network.PlacesRetrofit
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.TAG

class LocationRepository(application: Application) {

    private var locDao: LocationDao?

    init{
        val db = LocationDatabase.getDatabase(application)
        locDao = db?.locationDao()
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val placesLiveData: MutableLiveData<List<PlacesResult>> = MutableLiveData()
    private val placeRetrofit : PlacesRetrofit = PlacesRetrofit()

    fun getPlaceResults(location: String, radius: String){
        compositeDisposable.add(
            placeRetrofit.getNearBy(location, radius)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { it.results }
                .subscribe({
                    placesLiveData.postValue(it)
                    insertLocationDB(LocationPlace())
                    compositeDisposable.clear()
                },{
                    Log.d(TAG, "getPlaceResults: $it")
                })
        )

    }

    fun insertLocationDB(locationPlace: LocationPlace){
        locDao?.insertLocation(locationPlace)
    }

    fun deleteLocationDB(locationPlace: LocationPlace){
        locDao?.deleteLocation(locationPlace)
    }

    fun updateLocationDB(locationPlace: LocationPlace){
        locDao?.updateLocation(locationPlace)
    }

    fun clearLocationDB(){
        locDao?.deleteAllLocations()
    }

    fun getAllLocationDB() = locDao?.getAllLocations()


}