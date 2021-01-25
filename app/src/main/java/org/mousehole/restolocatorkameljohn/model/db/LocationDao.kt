package org.mousehole.restolocatorkameljohn.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import org.mousehole.restolocatorkameljohn.model.data.Location
import org.mousehole.restolocatorkameljohn.model.data.LocationPlace

@Dao
interface LocationDao {

    @Insert
    fun insertLocation(locationPlace: LocationPlace)

    @Update
    fun updateLocation(locationPlace: LocationPlace)

    @Delete
    fun deleteLocation(locationPlace: LocationPlace)

    @Query("DELETE FROM location_table")
    fun deleteAllLocations()

    @Query("SELECT * FROM location_table")
    fun getAllLocations(): LiveData<List<LocationPlace>>


}