package org.mousehole.restolocatorkameljohn.network

import io.reactivex.Observable
import org.mousehole.restolocatorkameljohn.model.data.PlacesResponse
import org.mousehole.restolocatorkameljohn.model.data.PlacesResult
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.KEY
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.LOCATION
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.RADIUS
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.URL_PATH
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAPI {

    @GET(URL_PATH)
    fun searchNearBy (@Query(LOCATION) searchLocation: String,
                      @Query(RADIUS) searchRadius: String,
                      @Query(KEY) searchApiKey: String): Observable<PlacesResponse>

}